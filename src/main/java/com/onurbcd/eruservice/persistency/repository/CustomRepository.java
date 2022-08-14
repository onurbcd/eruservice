package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.Dtoable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CustomRepository<D extends Dtoable> {

    Page<D> getAll(Predicate predicate, Pageable pageable);

    @Nullable
    D getSingle(UUID id);
}
