package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID>, QuerydslPredicateExecutor<Budget>,
        SequenceRepository {

    @Override
    @Query("select max(b.sequence)" +
            " from Budget b" +
            " where b.refYear = :#{#sequenceParam.year}" +
            " and b.refMonth = :#{#sequenceParam.month}")
    Short getMaxSequence(@Param("sequenceParam") SequenceParam sequenceParam);
}
