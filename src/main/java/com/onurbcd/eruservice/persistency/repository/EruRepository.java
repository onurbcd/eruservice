package com.onurbcd.eruservice.persistency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface EruRepository<E> extends JpaRepository<E, UUID>, QuerydslPredicateExecutor<E> {
}
