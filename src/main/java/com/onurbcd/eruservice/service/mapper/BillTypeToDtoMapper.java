package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.api.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillTypeToDtoMapper extends ToDtoMappable<BillType, BillTypeDto> {

    @Override
    BillTypeDto apply(BillType billType);
}
