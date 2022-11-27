package com.onurbcd.eruservice.persistency.repository.impl;

import com.onurbcd.eruservice.dto.source.SourceDto;
import com.onurbcd.eruservice.persistency.entity.QSource;
import com.onurbcd.eruservice.persistency.entity.Source;
import com.onurbcd.eruservice.persistency.predicate.SourcePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.UUID;

public class SourceRepositoryImpl implements CustomRepository<SourceDto, Source> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public SourceDto getSingle(UUID id) {
        var predicate = SourcePredicateBuilder.id(id);
        return columnsQuery(predicate).fetchFirst();
    }

    @Override
    public JPAQuery<Object> mainQuery(Predicate predicate) {
        return new JPAQuery<>(manager)
                .from(QSource.source)
                .innerJoin(QSource.source.incomeSource)
                .where(predicate);
    }

    @Override
    public QBean<SourceDto> columns() {
        return Projections.bean(
                SourceDto.class,
                QSource.source.createdDate,
                QSource.source.lastModifiedDate,
                QSource.source.id,
                QSource.source.name,
                QSource.source.active,
                QSource.source.incomeSource.id.as("incomeSourceId"),
                QSource.source.incomeSource.name.as("incomeSourceName"),
                QSource.source.sourceType,
                QSource.source.currencyType,
                QSource.source.balance
        );
    }

    @Override
    public EntityPathBase<Source> entityPathBase() {
        return QSource.source;
    }
}
