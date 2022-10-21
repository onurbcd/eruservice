package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.CategoryDto;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CategoryValidationService {

    void validate(CategoryDto dto, @Nullable CategoryDto current, @Nullable UUID id);

    void validateDelete(CategoryDto dto);
}
