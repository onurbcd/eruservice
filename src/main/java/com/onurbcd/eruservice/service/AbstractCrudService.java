package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.document.Documentable;
import com.onurbcd.eruservice.service.mapper.ToDtoMappable;
import com.onurbcd.eruservice.service.validation.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public abstract class AbstractCrudService<T extends Documentable, D extends Dtoable> implements CrudService {

    private final JpaRepository<T, UUID> repository;

    private final ToDtoMappable<T, D> toDtoMappable;

    protected AbstractCrudService(JpaRepository<T, UUID> repository, ToDtoMappable<T, D> toDtoMappable) {
        this.repository = repository;
        this.toDtoMappable = toDtoMappable;
    }

    @Override
    public Dtoable save(Dtoable dto, UUID id) {
        var currentSecret = id != null ? repository.findById(id).orElse(null) : null;
        validate(dto, currentSecret, id);
        @SuppressWarnings("unchecked") var secret = (T) fillValues(dto, currentSecret);
        secret = repository.save(secret);
        return toDtoMappable.apply(secret);
    }

    @Override
    public void delete(UUID id) {
        Action.checkIf(repository.existsById(id)).orElseThrowNotFound(id);
        repository.deleteById(id);
    }
}
