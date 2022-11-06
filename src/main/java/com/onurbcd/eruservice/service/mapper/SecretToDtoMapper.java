package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.dto.secret.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import com.onurbcd.eruservice.service.helper.Cryptoable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

@Mapper(config = DefaultMapperConfig.class)
public abstract class SecretToDtoMapper implements Function<Secret, SecretDto> {

    @Autowired
    protected Cryptoable cryptoable;

    @Override
    @Mapping(target = "password", expression = "java(cryptoable.decrypt(secret.getPassword()))")
    public abstract SecretDto apply(Secret secret);
}
