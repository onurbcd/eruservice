package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.repository.EruRepository;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.validation.Action;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.UUID;
import java.util.function.Function;

public abstract class AbstractCrudService<E extends Entityable, D extends Dtoable> implements CrudService {

    private final EruRepository<E, D> repository;

    private Function<E, D> toDtoMapper;

    private final QueryType queryType;

    protected AbstractCrudService(EruRepository<E, D> repository, Function<E, D> toDtoMapper, QueryType queryType) {
        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
        this.queryType = queryType;
    }

    protected AbstractCrudService(EruRepository<E, D> repository, QueryType queryType) {
        this.repository = repository;
        this.queryType = queryType;
    }

    protected abstract Predicate getPredicate(Filterable filter);

    @Override
    public void save(Dtoable dto, @Nullable UUID id) {
        var currentEntity = id != null ? repository.findById(id).orElse(null) : null;
        validate(dto, currentEntity, id);
        @SuppressWarnings("unchecked") var newEntity = (E) fillValues(dto, currentEntity);
        repository.save(newEntity);
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
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

    protected E findByIdOrElseThrow(UUID id) {
        var entity = repository.findById(id).orElse(null);
        Action.checkIfNotNull(entity).orElseThrowNotFound(id);
        return entity;
    }
}
