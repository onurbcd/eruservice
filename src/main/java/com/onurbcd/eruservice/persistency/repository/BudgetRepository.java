package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.BudgetSumDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BudgetRepository extends EruRepository<Budget, BudgetDto>, SequenceRepository {

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

    @Override
    @Query("select count(*)" +
            " from Budget b" +
            " where b.refYear = :#{#sequenceParam.year}" +
            " and b.refMonth = :#{#sequenceParam.month}" +
            " and b.sequence > :#{#sequenceParam.sequence}")
    long countNextSequences(SequenceParam sequenceParam);

    @Query("select new com.onurbcd.eruservice.dto.BudgetSumDto(sum(b.amount), b.paid, count(*))" +
            " from Budget b" +
            " where b.refYear = :refYear" +
            " and b.refMonth = :refMonth" +
            " group by b.paid")
    Set<BudgetSumDto> getSumByMonth(@Param("refYear") Short refYear, @Param("refMonth") Short refMonth);

    @Modifying
    @Query("delete from Budget b where b.refYear = :refYear and b.refMonth = :refMonth")
    int deleteAll(@Param("refYear") Short refYear, @Param("refMonth") Short refMonth);
}
