package com.onurbcd.eruservice.persistency.repository.impl;

import com.onurbcd.eruservice.dto.source.SourceDto;
import com.onurbcd.eruservice.persistency.entity.QSource;
import com.onurbcd.eruservice.persistency.predicate.SourcePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SourceRepositoryImpl implements CustomRepository<SourceDto> {

    private static final QBean<SourceDto> COLUMNS = Projections.bean(
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

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<SourceDto> getAll(Predicate predicate, Pageable pageable) {
        var content = new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QSource.source)
                .innerJoin(QSource.source.incomeSource)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderBy(pageable.getSort(), QSource.source.getMetadata().getName()))
                .fetch();

        final var totalSupplier = content.size() < pageable.getPageSize()
                ? content.size()
                : new JPAQuery<>(manager).from(QSource.source).innerJoin(QSource.source.incomeSource).where(predicate)
                .fetchCount();

        return PageableExecutionUtils.getPage(content, pageable, () -> totalSupplier);
    }

    @Override
    public List<SourceDto> getAll(Predicate predicate) {
        return Collections.emptyList();
    }

    @Override
    public SourceDto getSingle(UUID id) {
        var predicate = SourcePredicateBuilder.id(id);

        return new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QSource.source)
                .innerJoin(QSource.source.incomeSource)
                .where(predicate)
                .fetchFirst();
    }
}
