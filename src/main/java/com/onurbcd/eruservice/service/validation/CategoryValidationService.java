package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CategoryValidationService {

    void validate(CategoryDto categoryDto, @Nullable Category category, @Nullable UUID id);

    void validateDelete(Category category);
}
