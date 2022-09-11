package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MiddlewareRepository<Category, CategoryDto> {
}
