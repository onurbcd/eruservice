package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.secret.SecretSaveDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface SecretValidationService {

    void validate(SecretSaveDto dto, @Nullable Secret secret, @Nullable UUID id);
}
