package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.filter.CategoryFilter;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CategoryRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.CategoryToEntityMapper;
import com.onurbcd.eruservice.service.validation.CategoryValidationService;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryServiceImpl extends AbstractCrudService<Category, CategoryDto, CategoryPredicateBuilder>
        implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryValidationService validationService;

    public CategoryServiceImpl(CategoryRepository repository, CategoryToEntityMapper toEntityMapper,
                               CategoryValidationService validationService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, CategoryPredicateBuilder.class);
        this.repository = repository;
        this.validationService = validationService;
    }

    @Override
    @Transactional
    public void save(Dtoable dto, UUID id) {
        super.save(dto, id);
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        validationService.validate((CategoryDto) dto, (Category) entity, id);
    }

    @Override
    public Category fillValues(Dtoable dto, Entityable entity) {
        var category = (Category) super.fillValues(dto, entity);
        var categoryDto = (CategoryDto) dto;
        var parent = (CategoryDto) getById(categoryDto.getParentId());

        if (entity == null) {
            category.setLevel((short) (parent.getLevel() + 1));
            category.setLastBranch(Boolean.TRUE);
        } else {
            var current = (Category) entity;
            category.setParent(new Category(parent.getId()));
            category.setLevel(current.getLevel());
            category.setLastBranch(current.getLastBranch());
        }

        return category;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var category = findByIdOrElseThrow(id);
        validationService.validateDelete(category);
        repository.deleteById(id);
        repository.updateLastBranch(Boolean.TRUE, category.getParent().getId());
    }

    @Override
    public void afterSave(Entityable entity, UUID id) {
        var category = (Category) entity;

        if (id == null) {
            repository.updateLastBranch(Boolean.FALSE, category.getParent().getId());
        }
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return CategoryPredicateBuilder.all((CategoryFilter) filter);
    }
}
