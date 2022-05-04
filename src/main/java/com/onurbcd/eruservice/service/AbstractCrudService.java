package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Documentable;
import com.onurbcd.eruservice.service.mapper.ToDtoMappable;
import com.onurbcd.eruservice.service.validation.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public abstract class AbstractCrudService<T extends Documentable, D extends Dtoable> implements CrudService {

    private final JpaRepository<T, UUID> repository;

    private final ToDtoMappable<T, D> toDtoMapper;

    protected AbstractCrudService(JpaRepository<T, UUID> repository, ToDtoMappable<T, D> toDtoMapper) {
        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
    }

    @Override
    public Dtoable save(Dtoable dto, UUID id) {
        var currentDoc = id != null ? repository.findById(id).orElse(null) : null;
        validate(dto, currentDoc, id);
        @SuppressWarnings("unchecked") var newDoc = (T) fillValues(dto, currentDoc);
        newDoc = repository.save(newDoc);
        return toDtoMapper.apply(newDoc);
    }

    @Override
    public Dtoable getById(UUID id) {
        var doc = repository.findById(id).orElse(null);
        Action.checkIfNotNull(doc).orElseThrowNotFound(id);
        return toDtoMapper.apply(doc);
    }

    @Override
    public void delete(UUID id) {
        Action.checkIf(repository.existsById(id)).orElseThrowNotFound(id);
        repository.deleteById(id);
    }
}
