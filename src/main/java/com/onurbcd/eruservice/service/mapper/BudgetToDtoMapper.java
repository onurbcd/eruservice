package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetToDtoMapper extends ToDtoMappable<Budget, BudgetDto> {

    @Override
    BudgetDto apply(Budget budget);
}
