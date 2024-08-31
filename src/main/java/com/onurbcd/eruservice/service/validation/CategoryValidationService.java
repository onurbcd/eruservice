package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.dto.category.CategorySaveDto;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CategoryValidationService {

    void validate(CategorySaveDto saveDto, @Nullable CategoryDto current, @Nullable UUID id);

    void validateDelete(CategoryDto dto);
}
