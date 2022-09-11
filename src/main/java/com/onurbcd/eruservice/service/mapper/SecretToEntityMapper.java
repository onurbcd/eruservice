package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(config = DefaultMapperConfig.class)
public interface SecretToEntityMapper extends Function<SecretDto, Secret> {
}
