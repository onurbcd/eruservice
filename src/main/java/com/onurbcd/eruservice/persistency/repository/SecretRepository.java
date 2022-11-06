package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.secret.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends MiddlewareRepository<Secret, SecretDto> {
}
