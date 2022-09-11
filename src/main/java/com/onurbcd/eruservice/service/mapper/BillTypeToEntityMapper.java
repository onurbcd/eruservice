package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface BillTypeToEntityMapper extends EntityMapper<BillTypeDto, BillType> {
}
