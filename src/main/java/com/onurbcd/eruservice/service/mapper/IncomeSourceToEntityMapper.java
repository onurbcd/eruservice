package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.incomesource.IncomeSourceSaveDto;
import com.onurbcd.eruservice.persistency.entity.IncomeSource;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface IncomeSourceToEntityMapper extends EntityMapper<IncomeSourceSaveDto, IncomeSource> {
}
