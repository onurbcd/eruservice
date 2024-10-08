package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.category.CategorySaveDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface CategoryToEntityMapper extends EntityMapper<CategorySaveDto, Category> {

    @Override
    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Category apply(CategorySaveDto categorySaveDto);
}
