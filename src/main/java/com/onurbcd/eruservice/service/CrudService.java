package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Documentable;
import com.onurbcd.eruservice.service.filter.Filterable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CrudService {

    Dtoable save(Dtoable dto, UUID id);

    void validate(Dtoable dto, Documentable doc, UUID id);

    Documentable fillValues(Dtoable dto, Documentable doc);

    void delete(UUID id);

    Dtoable getById(UUID id);

    Page<Dtoable> getAll(Pageable pageable, Filterable filter);
}
