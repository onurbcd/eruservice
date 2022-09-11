package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
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

    public CategoryServiceImpl(CategoryRepository repository, CategoryToDtoMapper toDtoMapper,
                               CategoryToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA);
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
