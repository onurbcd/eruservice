package com.onurbcd.eruservice.service.validation.impl;

import com.onurbcd.eruservice.dto.secret.SecretSaveDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.SecretValidationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecretValidationServiceImpl implements SecretValidationService {

    @Override
    public void validate(SecretSaveDto dto, Secret secret, UUID id) {
        Action.checkIf(id == null || secret != null)
                .orElseThrowNotFound(id);
    }
}
