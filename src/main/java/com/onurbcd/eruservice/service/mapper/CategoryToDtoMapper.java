package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(config = DefaultMapperConfig.class)
public interface CategoryToDtoMapper extends Function<Category, CategoryDto> {
}
