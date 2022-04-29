package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.persistency.document.Documentable;
import com.onurbcd.eruservice.service.filter.Filterable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService {

    Dtoable save(Dtoable dto, String id);

    void validate(Dtoable dto, Documentable doc, String id);

    Documentable fillValues(Dtoable dto, Documentable doc);

    void delete(String id);

    Dtoable getById(String id);

    Page<Dtoable> getAll(Pageable pageable, Filterable filter);
}
