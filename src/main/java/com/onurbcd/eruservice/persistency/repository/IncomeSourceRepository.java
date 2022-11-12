package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.incomesource.IncomeSourceDto;
import com.onurbcd.eruservice.persistency.entity.IncomeSource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncomeSourceRepository extends MiddlewareRepository<IncomeSource, IncomeSourceDto> {

    @Override
    @Modifying
    @Query("delete from IncomeSource i where i.id = :id")
    int deleteUsingId(UUID id);
}
