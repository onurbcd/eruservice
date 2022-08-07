package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetToDtoMapper extends ToDtoMappable<Budget, BudgetDto> {

    @Override
    @Mapping(source = "billType.id", target = "billTypeId")
    @Mapping(source = "billType.name", target = "billTypeName")
    BudgetDto apply(Budget budget);
}
