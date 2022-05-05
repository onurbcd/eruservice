package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.service.filter.Filterable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CrudService {

    Dtoable save(Dtoable dto, UUID id);

    void validate(Dtoable dto, Entityable entity, UUID id);

    Entityable fillValues(Dtoable dto, Entityable entity);

    void delete(UUID id);

    Dtoable getById(UUID id);

    Page<Dtoable> getAll(Pageable pageable, Filterable filter);

    void update(Dtoable dto, UUID id);
}
