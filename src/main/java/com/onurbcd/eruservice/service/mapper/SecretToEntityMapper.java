package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface SecretToEntityMapper extends Function<SecretDto, Secret> {

    @Override
    Secret apply(SecretDto secretDto);
}
