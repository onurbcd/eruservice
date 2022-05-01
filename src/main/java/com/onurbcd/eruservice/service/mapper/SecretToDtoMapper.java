package com.onurbcd.eruservice.service.mapper;

import com.onurbcd.eruservice.api.dto.SecretDto;
import com.onurbcd.eruservice.persistency.document.Secret;
import com.onurbcd.eruservice.service.helper.Cryptoable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SecretToDtoMapper implements ToDtoMappable<Secret, SecretDto> {

    @Autowired
    protected Cryptoable cryptoable;

    @Override
    @Mapping(target = "password", expression = "java(cryptoable.decrypt(secret.getPassword()))")
    public abstract SecretDto apply(Secret secret);
}
