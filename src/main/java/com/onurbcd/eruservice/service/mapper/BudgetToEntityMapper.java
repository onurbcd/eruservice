package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface BudgetToEntityMapper extends Function<BudgetDto, Budget> {

    @Override
    @Mapping(source = "billTypeId", target = "billType.id")
    @Mapping(source = "billTypeName", target = "billType.name")
    Budget apply(BudgetDto budgetDto);
}
