package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
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
    Short getMaxSequence(SequenceParam sequenceParam);

    @Override
    @Query("select case b.id when null then false else true end" +
            " from Budget b" +
            " where b.refYear = :#{#sequenceParam.year}" +
            " and b.refMonth = :#{#sequenceParam.month}" +
            " and b.sequence = :#{#sequenceParam.sequence}")
    Boolean existsSequence(SequenceParam sequenceParam);

    @Override
    @Modifying
    @Query("update Budget b" +
            " set b.sequence = :#{#sequenceParam.targetSequence}" +
            " where b.refYear = :#{#sequenceParam.year}" +
            " and b.refMonth = :#{#sequenceParam.month}" +
            " and b.sequence = :#{#sequenceParam.sequence}")
    void updateSequence(SequenceParam sequenceParam);
}
