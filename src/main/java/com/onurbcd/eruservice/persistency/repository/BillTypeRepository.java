package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.billtype.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillTypeRepository extends MiddlewareRepository<BillType, BillTypeDto> {

    @Override
    @Modifying
    @Query("delete from BillType b where b.id = :id")
    int deleteUsingId(UUID id);

    @Override
    @Modifying
    @Query("update BillType b set b.active = :active where b.id = :id")
    int updateActive(UUID id, Boolean active);
}
