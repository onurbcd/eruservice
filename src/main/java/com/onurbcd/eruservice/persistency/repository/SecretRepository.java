package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.secret.SecretDto;
import com.onurbcd.eruservice.persistency.entity.Secret;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecretRepository extends MiddlewareRepository<Secret, SecretDto> {

    @Override
    @Modifying
    @Query("delete from Secret s where s.id = :id")
    int deleteUsingId(UUID id);

    @Override
    @Modifying
    @Query("update Secret s set s.active = :active where s.id = :id")
    int updateActive(UUID id, Boolean active);
}
