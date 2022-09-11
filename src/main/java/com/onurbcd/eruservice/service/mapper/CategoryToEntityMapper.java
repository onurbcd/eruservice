package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface CategoryToEntityMapper extends EntityMapper<CategoryDto, Category> {
}
