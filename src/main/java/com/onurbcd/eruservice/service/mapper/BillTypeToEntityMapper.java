package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.billtype.BillTypeSaveDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface BillTypeToEntityMapper extends EntityMapper<BillTypeSaveDto, BillType> {
}
