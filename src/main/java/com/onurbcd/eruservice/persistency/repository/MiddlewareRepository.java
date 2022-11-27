package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface MiddlewareRepository<E extends Entityable, D extends Dtoable> extends EruRepository<E, D> {

    @Override
    default D getSingle(UUID id) {
        return null;
    }

    @Override
    default JPAQuery<Object> mainQuery(Predicate predicate) {
        return new JPAQuery<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    default QBean<D> columns() {
        return (QBean<D>) Projections.bean(Dtoable.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    default EntityPathBase<E> entityPathBase() {
        return new EntityPathBase<>((Class<? extends E>) Entityable.class, StringUtils.EMPTY);
    }
}
