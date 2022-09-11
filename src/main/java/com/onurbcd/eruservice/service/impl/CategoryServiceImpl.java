package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CategoryRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.filter.CategoryFilter;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.CategoryToDtoMapper;
import com.onurbcd.eruservice.service.mapper.CategoryToEntityMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractCrudService<Category, CategoryDto> implements CategoryService {

    private final CategoryToEntityMapper toEntityMapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryToDtoMapper toDtoMapper,
                               CategoryToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, QueryType.JPA);
        this.toEntityMapper = toEntityMapper;
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        var categoryDto = (CategoryDto) dto;
        var currentCategory = (Category) entity;
        var category = toEntityMapper.apply(categoryDto);
        category.setId(currentCategory != null ? currentCategory.getId() : null);

        if (currentCategory != null) {
            category.setCreatedDate(currentCategory.getCreatedDate());
        }

        return category;
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        var categoryFilter = (CategoryFilter) filter;

        return CategoryPredicateBuilder
                .of()
                .name(categoryFilter.getSearch())
                .active(categoryFilter.isActive())
                .build();
    }
}
