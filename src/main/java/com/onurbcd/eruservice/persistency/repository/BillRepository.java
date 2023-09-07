package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.bill.BillDto;
import com.onurbcd.eruservice.persistency.entity.Bill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepository extends EruRepository<Bill, BillDto> {

    @Override
    @Modifying
    @Query("delete" +
            " from Bill b" +
            " where b.id = :id")
    int deleteUsingId(UUID id);

    @Override
    @Modifying
    @Query("update Bill b" +
            " set b.active = :active" +
            " where b.id = :id")
    int updateActive(UUID id, Boolean active);
}
