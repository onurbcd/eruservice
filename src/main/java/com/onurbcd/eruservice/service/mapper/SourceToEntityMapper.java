package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.source.SourceSaveDto;
import com.onurbcd.eruservice.persistency.entity.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface SourceToEntityMapper extends EntityMapper<SourceSaveDto, Source> {

    @Override
    @Mapping(source = "incomeSourceId", target = "incomeSource.id")
    Source apply(SourceSaveDto sourceSaveDto);
}