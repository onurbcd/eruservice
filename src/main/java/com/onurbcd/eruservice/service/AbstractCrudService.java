package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.predicate.BasePredicateBuilder;
import com.onurbcd.eruservice.persistency.predicate.PredicateBuilderFactory;
import com.onurbcd.eruservice.persistency.repository.EruRepository;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.EntityMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.UUID;
import java.util.function.Function;

public abstract class AbstractCrudService<E extends Entityable, D extends Dtoable, P extends BasePredicateBuilder>
        implements CrudService {

    private final EruRepository<E, D> repository;

    private Function<E, D> toDtoMapper;

    private final EntityMapper<D, E> toEntityMapper;

    private final QueryType queryType;

    private final Class<P> predicateClass;

    protected AbstractCrudService(EruRepository<E, D> repository, Function<E, D> toDtoMapper,
                                  EntityMapper<D, E> toEntityMapper, QueryType queryType, Class<P> predicateClass) {

        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
        this.toEntityMapper = toEntityMapper;
        this.queryType = queryType;
        this.predicateClass = predicateClass;
    }

    protected AbstractCrudService(EruRepository<E, D> repository, EntityMapper<D, E> toEntityMapper,
                                  QueryType queryType, Class<P> predicateClass) {

        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.queryType = queryType;
        this.predicateClass = predicateClass;
    }

    @Override
    public void save(Dtoable dto, @Nullable UUID id) {
        var currentEntity = id != null ? repository.findById(id).orElse(null) : null;
        validate(dto, currentEntity, id);
        @SuppressWarnings("unchecked") var newEntity = (E) fillValues(dto, currentEntity);
        repository.save(newEntity);
        afterSave(newEntity, id);
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        @SuppressWarnings("unchecked") var newEntity = toEntityMapper.apply((D) dto);
        newEntity.setId(entity != null ? entity.getId() : null);

        if (entity != null) {
            newEntity.setCreatedDate(entity.getCreatedDate());
        }

        return newEntity;
    }

    @Override
    public Dtoable getById(UUID id) {
        if (QueryType.JPA.equals(queryType)) {
            var entity = findByIdOrElseThrow(id);
            return toDtoMapper.apply(entity);
        }

        var dto = repository.getSingle(id);
        Action.checkIfNotNull(dto).orElseThrowNotFound(id);
        return dto;
    }

    @Override
    public void delete(UUID id) {
        Action.checkIf(repository.existsById(id)).orElseThrowNotFound(id);
        repository.deleteById(id);
    }

    @Override
    public void update(Dtoable dto, UUID id) {
        var entity = repository.findById(id).orElse(null);
        Action.checkIfNotNull(entity).orElseThrowNotFound(id);
        assert entity != null;
        entity.setActive(dto.isActive());
        repository.save(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Dtoable> getAll(Pageable pageable, Filterable filter) {
        var predicate = getPredicate(filter);

        return QueryType.JPA.equals(queryType)
                ? repository.findAll(predicate, pageable).map(toDtoMapper)
                : (Page<Dtoable>) repository.getAll(predicate, pageable);
    }

    @Override
    public void afterSave(Entityable entity, UUID id) {
    }

    protected E findByIdOrElseThrow(UUID id) {
        var entity = repository.findById(id).orElse(null);
        Action.checkIfNotNull(entity).orElseThrowNotFound(id);
        return entity;
    }

    protected Predicate getPredicate(Filterable filter) {
        var predicate = PredicateBuilderFactory.init(predicateClass);
        return predicate.search(filter.getSearch()).active(filter.isActive()).build();
    }
}
