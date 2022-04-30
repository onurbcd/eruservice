package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.document.Secret;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecretRepository extends JpaRepository<Secret, UUID> {

    @Query("  select s" +
            " from Secret s" +
            " where lower(s.name) like %:search%" +
            " or lower(s.description) like %:search%" +
            " or lower(s.link) like %:search%" +
            " or lower(s.username) like %:search%")
    Page<Secret> getAll(@Param("search") String search, Pageable pageable);
}
