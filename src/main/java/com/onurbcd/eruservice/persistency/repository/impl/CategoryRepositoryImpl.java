package com.onurbcd.eruservice.persistency.repository.impl;

import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.entity.QCategory;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.UUID;

public class CategoryRepositoryImpl implements CustomRepository<CategoryDto, Category> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public CategoryDto getSingle(UUID id) {
        var predicate = CategoryPredicateBuilder.id(id);
        return columnsQuery(predicate).fetchFirst();
    }

    @Override
    public JPAQuery<Object> mainQuery(Predicate predicate) {
        return new JPAQuery<>(manager)
                .from(QCategory.category)
                .leftJoin(QCategory.category.parent)
                .where(predicate);
    }

    @Override
    public QBean<CategoryDto> columns() {
        return Projections.bean(
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
    }

    @Override
    public EntityPathBase<Category> entityPathBase() {
        return QCategory.category;
    }
}
