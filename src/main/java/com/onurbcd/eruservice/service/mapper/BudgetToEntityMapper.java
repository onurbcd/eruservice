package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;

@Mapper(config = DefaultMapperConfig.class)
public interface BudgetToEntityMapper extends Function<BudgetDto, Budget> {

    @Override
    @Mapping(source = "billTypeId", target = "billType.id")
    @Mapping(source = "billTypeName", target = "billType.name")
    Budget apply(BudgetDto budgetDto);

    @Mapping(source = "budgetDto.billTypeId", target = "billType.id")
    @Mapping(target = "createdDate", expression = "java(null)")
    @Mapping(target = "lastModifiedDate", expression = "java(null)")
    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "active", expression = "java(Boolean.TRUE)")
    @Mapping(target = "refYear", expression = "java(refYear)")
    @Mapping(target = "refMonth", expression = "java(refMonth)")
    @Mapping(target = "paid", expression = "java(Boolean.FALSE)")
    Budget copy(BudgetDto budgetDto, Short refYear, Short refMonth);
}
