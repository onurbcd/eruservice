package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.api.dto.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface SecretToDocMapper extends Function<SecretDto, Secret> {

    @Override
    Secret apply(SecretDto secretDto);
}
