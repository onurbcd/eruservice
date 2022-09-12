package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CategoryRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.CategoryToDtoMapper;
import com.onurbcd.eruservice.service.mapper.CategoryToEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractCrudService<Category, CategoryDto, CategoryPredicateBuilder>
        implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository, CategoryToDtoMapper toDtoMapper,
                               CategoryToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA, CategoryPredicateBuilder.class);
    }
}
