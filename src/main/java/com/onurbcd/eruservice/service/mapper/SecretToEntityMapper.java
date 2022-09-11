package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface SecretToEntityMapper extends EntityMapper<SecretDto, Secret> {
}
