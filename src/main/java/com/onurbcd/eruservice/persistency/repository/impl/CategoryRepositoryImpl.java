package com.onurbcd.eruservice.persistency.repository.impl;

import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.QCategory;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import java.util.Collections;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class CategoryRepositoryImpl implements CustomRepository<CategoryDto> {

    private static final QBean<CategoryDto> COLUMNS = Projections.bean(
            CategoryDto.class,
            QCategory.category.createdDate,
            QCategory.category.lastModifiedDate,
            QCategory.category.id,
            QCategory.category.name,
            QCategory.category.active,
            QCategory.category.parent.id.as("parentId"),
            QCategory.category.parent.name.as("parentName"),
            QCategory.category.level,
            QCategory.category.lastBranch,
            QCategory.category.description
    );

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CategoryDto> getAll(Predicate predicate, Pageable pageable) {
        var content = new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QCategory.category)
                .leftJoin(QCategory.category.parent)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderBy(pageable.getSort(), QCategory.category.getMetadata().getName()))
                .fetch();

        final var totalSupplier = content.size() < pageable.getPageSize()
                ? content.size()
                : new JPAQuery<>(manager).from(QCategory.category).leftJoin(QCategory.category.parent).where(predicate)
                .fetchCount();

        return PageableExecutionUtils.getPage(content, pageable, () -> totalSupplier);
    }

    @Override
    public List<CategoryDto> getAll(Predicate predicate) {
        return Collections.emptyList();
    }

    @Override
    public CategoryDto getSingle(UUID id) {
        var predicate = CategoryPredicateBuilder.id(id);

        return new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QCategory.category)
                .leftJoin(QCategory.category.parent)
                .where(predicate)
                .fetchFirst();
    }
}
