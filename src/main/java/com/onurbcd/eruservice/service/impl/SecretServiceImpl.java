package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.secret.SecretDto;
import com.onurbcd.eruservice.dto.secret.SecretSaveDto;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.entity.Secret;
import com.onurbcd.eruservice.persistency.predicate.SecretPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.SecretRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.helper.Cryptoable;
import com.onurbcd.eruservice.service.mapper.SecretToEntityMapper;
import com.onurbcd.eruservice.service.mapper.SecretToDtoMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.dto.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecretServiceImpl extends AbstractCrudService<Secret, SecretDto, SecretPredicateBuilder, SecretSaveDto>
        implements SecretService {

    private final Cryptoable cryptoable;

    public SecretServiceImpl(SecretRepository repository, Cryptoable cryptoable, SecretToDtoMapper toDtoMapper,
                             SecretToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA, SecretPredicateBuilder.class);
        this.cryptoable = cryptoable;
    }

    @Override
    public void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
        var secretDto = (SecretSaveDto) dto;

        Action.checkIfSizeBetween(secretDto.getPassword(), Constants.SIZE_3, Constants.SIZE_50)
                .orElseThrow(Error.SIZE_NOT_BETWEEN, "password", Constants.SIZE_3, Constants.SIZE_50);
    }

    @Override
    public Secret fillValues(Dtoable dto, Entityable entity) {
        var secret = (Secret) super.fillValues(dto, entity);

        if (StringUtils.isNotBlank(secret.getPassword())) {
            secret.setPassword(cryptoable.encrypt(secret.getPassword()));
        }

        return secret;
    }
}
