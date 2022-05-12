package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.api.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface BillTypeToEntityMapper extends Function<BillTypeDto, BillType> {

    @Override
    BillType apply(BillTypeDto billTypeDto);
}
