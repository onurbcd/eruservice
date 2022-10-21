package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.filter.CategoryFilter;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CategoryRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.CategoryToEntityMapper;
import com.onurbcd.eruservice.service.validation.CategoryValidationService;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
public class CategoryServiceImpl extends AbstractCrudService<Category, CategoryDto, CategoryPredicateBuilder>
        implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryToEntityMapper toEntityMapper;

    private final CategoryValidationService validationService;

    public CategoryServiceImpl(CategoryRepository repository, CategoryToEntityMapper toEntityMapper,
                               CategoryValidationService validationService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, CategoryPredicateBuilder.class);
        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.validationService = validationService;
    }

    @Override
    @Transactional
    public void save(Dtoable dto, UUID id) {
        var categoryDto = (CategoryDto) dto;
        var current = id != null ? (CategoryDto) getById(id) : null;
        validationService.validate(categoryDto, current, id);
        var parent = id == null ? (CategoryDto) getById(categoryDto.getParentId()) : null;
        var category = fillValues(categoryDto, current, parent);
        repository.save(category);

        if (id == null && Boolean.TRUE.equals(parent.getLastBranch())) {
            repository.updateLastBranch(Boolean.FALSE, parent.getId());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var category = (CategoryDto) getById(id);
        validationService.validateDelete(category);
        repository.deleteById(id);
        repository.updateLastBranch(Boolean.TRUE, category.getParentId());
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return CategoryPredicateBuilder.all((CategoryFilter) filter);
    }

    private Category fillValues(CategoryDto dto, @Nullable CategoryDto current, @Nullable CategoryDto parent) {
        var category = toEntityMapper.apply(dto);
        category.setCreatedDate(current != null ? current.getCreatedDate() : null);
        category.setId(current != null ? current.getId() : null);
        category.setParent(new Category(current != null ? current.getParentId() : dto.getParentId()));
        category.setLevel(current != null ? current.getLevel() : (short) (Objects.requireNonNull(parent).getLevel() + 1));
        category.setLastBranch(current != null ? current.getLastBranch() : Boolean.TRUE);
        return category;
    }
}
