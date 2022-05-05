package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.service.mapper.ToDtoMappable;
import com.onurbcd.eruservice.service.validation.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public abstract class AbstractCrudService<T extends Entityable, D extends Dtoable> implements CrudService {

    private final JpaRepository<T, UUID> repository;

    private final ToDtoMappable<T, D> toDtoMapper;

    protected AbstractCrudService(JpaRepository<T, UUID> repository, ToDtoMappable<T, D> toDtoMapper) {
        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
    }

    @Override
    public Dtoable save(Dtoable dto, UUID id) {
        var currentEntity = id != null ? repository.findById(id).orElse(null) : null;
        validate(dto, currentEntity, id);
        @SuppressWarnings("unchecked") var newEntity = (T) fillValues(dto, currentEntity);
        newEntity = repository.save(newEntity);
        return toDtoMapper.apply(newEntity);
    }

    @Override
    public Dtoable getById(UUID id) {
        var entity = repository.findById(id).orElse(null);
        Action.checkIfNotNull(entity).orElseThrowNotFound(id);
        return toDtoMapper.apply(entity);
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
}
