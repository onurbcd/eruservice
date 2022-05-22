package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.api.enums.Error;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.entity.Secret;
import com.onurbcd.eruservice.persistency.predicate.SecretPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.SecretRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.filter.SecretFilter;
import com.onurbcd.eruservice.service.helper.Cryptoable;
import com.onurbcd.eruservice.service.mapper.SecretToEntityMapper;
import com.onurbcd.eruservice.service.mapper.SecretToDtoMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.Constants;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecretServiceImpl extends AbstractCrudService<Secret, SecretDto> implements SecretService {

    private final SecretRepository repository;

    private final Cryptoable cryptoable;

    private final SecretToDtoMapper toDtoMapper;

    private final SecretToEntityMapper toEntityMapper;

    @Autowired
    public SecretServiceImpl(SecretRepository repository, Cryptoable cryptoable, SecretToDtoMapper toDtoMapper,
                             SecretToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper);
        this.repository = repository;
        this.cryptoable = cryptoable;
        this.toDtoMapper = toDtoMapper;
        this.toEntityMapper = toEntityMapper;
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
        var secretDto = (SecretDto) dto;

        Action.checkIfSizeBetween(secretDto.getPassword(), Constants.SIZE_3, Constants.SIZE_50)
                .orElseThrow(Error.SIZE_NOT_BETWEEN, "password", Constants.SIZE_3, Constants.SIZE_50);
    }

    @Override
    public Secret fillValues(Dtoable dto, Entityable entity) {
        var secretDto = (SecretDto) dto;
        var currentSecret = (Secret) entity;
        var secret = toEntityMapper.apply(secretDto);
        secret.setId(currentSecret != null ? currentSecret.getId() : null);
        secret.setDescription(StringUtils.isNotBlank(secret.getDescription()) ? secret.getDescription() : null);
        secret.setLink(StringUtils.isNotBlank(secret.getLink()) ? secret.getLink() : null);

        if (StringUtils.isNotBlank(secret.getPassword())) {
            secret.setPassword(cryptoable.encrypt(secret.getPassword()));
        }

        if (currentSecret != null) {
            secret.setCreatedDate(currentSecret.getCreatedDate());
        }

        return secret;
    }

    @Override
    public Page<Dtoable> getAll(Pageable pageable, Filterable filter) {
        var secretFilter = (SecretFilter) filter;

        Predicate predicate = SecretPredicateBuilder
                .of()
                .search(secretFilter.getSearch())
                .active(secretFilter.isActive())
                .build();

        return repository.findAll(predicate, pageable).map(toDtoMapper);
    }
}
