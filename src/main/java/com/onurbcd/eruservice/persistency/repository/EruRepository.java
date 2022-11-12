package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@NoRepositoryBean
public interface EruRepository<E extends Entityable, D extends Dtoable> extends JpaRepository<E, UUID>,
        QuerydslPredicateExecutor<E>, CustomRepository<D> {

    default int deleteUsingId(@Param("id") UUID id) {
        return 0;
    }
}
