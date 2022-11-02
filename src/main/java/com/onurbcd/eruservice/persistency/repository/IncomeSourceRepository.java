package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.incomesource.IncomeSourceDto;
import com.onurbcd.eruservice.persistency.entity.IncomeSource;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeSourceRepository extends MiddlewareRepository<IncomeSource, IncomeSourceDto> {
}
