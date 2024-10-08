package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.category.CategorySaveDto;
import com.onurbcd.eruservice.dto.filter.CategoryFilter;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.persistency.predicate.CategoryPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CategoryRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.CategoryToEntityMapper;
import com.onurbcd.eruservice.service.validation.CategoryValidationService;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@PrimeService(Domain.CATEGORY)
public class CategoryService
        extends AbstractCrudService<Category, CategoryDto, CategoryPredicateBuilder, CategorySaveDto> {

    private final CategoryRepository repository;

    private final CategoryToEntityMapper toEntityMapper;

    private final CategoryValidationService validationService;

    public CategoryService(CategoryRepository repository, CategoryToEntityMapper toEntityMapper,
                           CategoryValidationService validationService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, CategoryPredicateBuilder.class);
        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.validationService = validationService;
    }

    @Override
    @Transactional
    public void save(Dtoable dto, UUID id) {
        var categoryDto = (CategorySaveDto) dto;
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
    public void delete(UUID id) {
        var category = (CategoryDto) getById(id);
        validationService.validateDelete(category);
        repository.deleteById(id);
        var childrenCount = repository.countChildren(category.getParentId());

        if (childrenCount == 0) {
            repository.updateLastBranch(Boolean.TRUE, category.getParentId());
        }
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return CategoryPredicateBuilder.all((CategoryFilter) filter);
    }

    private Category fillValues(CategorySaveDto dto, @Nullable CategoryDto current, @Nullable CategoryDto parent) {
        var category = toEntityMapper.apply(dto);
        category.setCreatedDate(current != null ? current.getCreatedDate() : null);
        category.setId(current != null ? current.getId() : null);
        category.setParent(new Category(current != null ? current.getParentId() : dto.getParentId()));
        category.setLevel(current != null ? current.getLevel() : (short) (Objects.requireNonNull(parent).getLevel() + 1));
        category.setLastBranch(current != null ? current.getLastBranch() : Boolean.TRUE);
        return category;
    }
}
