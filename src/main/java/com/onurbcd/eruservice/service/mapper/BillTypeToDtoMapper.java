package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(config = DefaultMapperConfig.class)
public interface BillTypeToDtoMapper extends Function<BillType, BillTypeDto> {
}
