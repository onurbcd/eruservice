package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.dto.filter.Filterable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CrudService {

    void save(Dtoable dto, @Nullable UUID id);

    void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id);

    Entityable fillValues(Dtoable dto, Entityable entity);

    @Transactional
    void delete(UUID id);

    Dtoable getById(UUID id);

    Page<Dtoable> getAll(Pageable pageable, Filterable filter);

    @Transactional
    void update(Dtoable dto, UUID id);
}
