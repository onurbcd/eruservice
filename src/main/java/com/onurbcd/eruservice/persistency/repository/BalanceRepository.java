package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.balance.BalanceDto;
import com.onurbcd.eruservice.persistency.entity.Balance;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface BalanceRepository extends EruRepository<Balance, BalanceDto>, SequenceRepository {

    @Override
    @Modifying
    @Query("delete from Balance b where b.id = :id")
    int deleteUsingId(UUID id);

    @Override
    @Modifying
    @Query("update Balance b set b.active = :active where b.id = :id")
    int updateActive(UUID id, Boolean active);

    @Override
    @Query("select max(b.sequence)" +
            " from Balance b" +
            " inner join b.day d" +
            " where d.calendarYear = :#{#sequenceParam.year}" +
            " and d.calendarMonth = :#{#sequenceParam.month}")
    Short getMaxSequence(SequenceParam sequenceParam);

    @Override
    @Query("select case b.id when null then false else true end" +
            " from Balance b" +
            " inner join b.day d" +
            " where d.calendarYear = :#{#sequenceParam.year}" +
            " and d.calendarMonth = :#{#sequenceParam.month}" +
            " and b.sequence = :#{#sequenceParam.sequence}")
    Boolean existsSequence(SequenceParam sequenceParam);

    @Override
    @Modifying
    @Query("update Balance b1" +
            " set b1.sequence = :#{#sequenceParam.targetSequence}" +
            " where b1.id in (" +
            "   select b2.id" +
            "   from Balance b2" +
            "   inner join b2.day d" +
            "   where d.calendarYear = :#{#sequenceParam.year}" +
            "   and d.calendarMonth = :#{#sequenceParam.month}" +
            "   and b2.sequence = :#{#sequenceParam.sequence})")
    void updateSequence(SequenceParam sequenceParam);

    @Override
    @Query("select count(*)" +
            " from Balance b" +
            " inner join b.day d" +
            " where d.calendarYear = :#{#sequenceParam.year}" +
            " and d.calendarMonth = :#{#sequenceParam.month}" +
            " and b.sequence > :#{#sequenceParam.sequence}")
    long countNextSequences(SequenceParam sequenceParam);

    @Query("select d.id" +
            " from Balance b" +
            " left join b.documents d" +
            " where b.id = :id")
    Set<UUID> getDocumentsIds(@Param("id") UUID id);

    @Query("select d" +
            " from Balance b" +
            " left join b.documents d" +
            " where b.id = :id")
    Set<Document> getDocuments(@Param("id") UUID id);
}
