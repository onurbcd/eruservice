package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface MiddlewareRepository<E extends Entityable, D extends Dtoable> extends EruRepository<E, D> {

    @Override
    default Page<D> getAll(Predicate predicate, Pageable pageable) {
        return Page.empty();
    }

    @Override
    default List<D> getAll(Predicate predicate) {
        return Collections.emptyList();
    }

    @Override
    default D getSingle(UUID id) {
        return null;
    }
}
