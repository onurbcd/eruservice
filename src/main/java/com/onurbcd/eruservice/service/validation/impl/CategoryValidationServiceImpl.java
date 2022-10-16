package com.onurbcd.eruservice.service.validation.impl;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.CategoryValidationService;
import com.onurbcd.eruservice.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class CategoryValidationServiceImpl implements CategoryValidationService {

    @Override
    public void validate(CategoryDto categoryDto, Category category, UUID id) {
        Action.checkIf(id == null || category != null).orElseThrowNotFound(id);
        Action.checkIfNotNull(categoryDto.getParentId()).orElseThrow(Error.CATEGORY_PARENT_IS_NULL);
        Action.checkIf(id == null || notLevelOne(category)).orElseThrow(Error.CATEGORY_LEVEL_ONE_IS_UNCHANGEABLE);
    }

    @Override
    public void validateDelete(Category category) {
        Action.checkIf(notLevelOne(category)).orElseThrow(Error.CATEGORY_CANNOT_DELETE_LEVEL_ONE);
        Action.checkIf(category.getLastBranch()).orElseThrow(Error.CATEGORY_DELETE_NON_LAST_BRANCH);
    }

    private boolean notLevelOne(Category category) {
        return NumberUtil.notEquals(Objects.requireNonNull(category).getLevel(), Short.valueOf("1"));
    }
}
