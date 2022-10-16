package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface CategoryToEntityMapper extends EntityMapper<CategoryDto, Category> {

    @Override
    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(source = "parentName", target = "parent.name")
    Category apply(CategoryDto categoryDto);
}
