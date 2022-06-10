package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface BudgetToEntityMapper extends Function<BudgetDto, Budget> {

    @Override
    Budget apply(BudgetDto budgetDto);
}
