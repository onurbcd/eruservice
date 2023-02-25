package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.persistency.entity.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface BalanceToEntityMapper extends EntityMapper<BalanceSaveDto, Balance> {

    @Override
    @Mapping(source = "dayCalendarDate", target = "day.calendarDate")
    @Mapping(source = "sourceId", target = "source.id")
    @Mapping(source = "categoryId", target = "category.id")
    Balance apply(BalanceSaveDto balanceSaveDto);
}