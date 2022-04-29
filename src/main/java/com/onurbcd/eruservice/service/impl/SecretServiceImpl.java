package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.api.dto.SecretDto;
import com.onurbcd.eruservice.api.enums.Error;
import com.onurbcd.eruservice.persistency.document.Documentable;
import com.onurbcd.eruservice.persistency.document.Secret;
import com.onurbcd.eruservice.persistency.repository.SecretRepository;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.filter.SecretFilter;
import com.onurbcd.eruservice.service.helper.Cryptoable;
import com.onurbcd.eruservice.service.mapper.SecretToDocMapper;
import com.onurbcd.eruservice.service.mapper.SecretToDtoMapper;
import com.onurbcd.eruservice.service.validation.Action;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {

    private static final int PASSWORD_MIN_LENGTH = 3;

    private final SecretRepository repository;

    private final Cryptoable cryptoable;

    private final SecretToDtoMapper toDtoMapper;

    private final SecretToDocMapper toDocMapper;

    @Autowired
    public SecretServiceImpl(SecretRepository repository, Cryptoable cryptoable, SecretToDtoMapper toDtoMapper,
                             SecretToDocMapper toDocMapper) {

        this.repository = repository;
        this.cryptoable = cryptoable;
        this.toDtoMapper = toDtoMapper;
        this.toDocMapper = toDocMapper;
    }

    @Override
    public SecretDto save(Dtoable dto, String id) {
        var secretDto = (SecretDto) dto;
        var currentSecret = id != null ? repository.findById(id).orElse(null) : null;
        validate(secretDto, currentSecret, id);
        var secret = fillValues(secretDto, currentSecret);
        secret = repository.save(secret);
        return toDtoMapper.apply(secret);
    }

    @Override
    public void validate(Dtoable dto, Documentable doc, String id) {
        Action.checkIf(id == null || doc != null).orElseThrowNotFound(id);
        var secretDto = (SecretDto) dto;

        Action.checkIfSizeGE(secretDto.getPassword(), PASSWORD_MIN_LENGTH)
                .orElseThrow(Error.SIZE_LESS_THAN, "password", PASSWORD_MIN_LENGTH);
    }

    @Override
    public Secret fillValues(Dtoable dto, Documentable doc) {
        var secretDto = (SecretDto) dto;
        var currentSecret = (Secret) doc;
        var secret = toDocMapper.apply(secretDto);
        secret.setId(currentSecret != null ? currentSecret.getId() : null);
        secret.setDescription(StringUtils.isNotBlank(secret.getDescription()) ? secret.getDescription() : null);
        secret.setLink(StringUtils.isNotBlank(secret.getLink()) ? secret.getLink() : null);
        secret.setPassword(secret.getPassword() != null ? cryptoable.encrypt(secret.getPassword()) : null);
        secret.setCreatedDate(currentSecret != null ? currentSecret.getCreatedDate() : null);
        return secret;
    }

    @Override
    public void delete(String id) {
        Action.checkIf(repository.existsById(id)).orElseThrowNotFound(id);
        repository.deleteById(id);
    }

    @Override
    public SecretDto getById(String id) {
        var secret = repository.findById(id).orElse(null);
        Action.checkIfNotNull(secret).orElseThrowNotFound(id);
        return toDtoMapper.apply(secret);
    }

    @Override
    public Page<Dtoable> getAll(Pageable pageable, Filterable filter) {
        var secretFilter = (SecretFilter) filter;

        return StringUtils.isNotBlank(secretFilter.getSearch())
                ? repository.getAll(secretFilter.getSearch().trim(), pageable).map(toDtoMapper)
                : repository.findAll(pageable).map(toDtoMapper);
    }
}
