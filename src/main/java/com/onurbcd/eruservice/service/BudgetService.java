package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.budget.CopyBudgetDto;
import com.onurbcd.eruservice.dto.budget.SumDto;
import com.onurbcd.eruservice.dto.filter.BudgetFilter;
import com.onurbcd.eruservice.dto.budget.BudgetValuesDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface BudgetService extends CrudService, Sequenceable {

    Set<SumDto> getSumByMonth(BudgetFilter filter);

    void copy(CopyBudgetDto copyBudgetDto);

    @Transactional
    void deleteAll(Short refYear, Short refMonth);

    BudgetValuesDto getBudgetValues(UUID id);
}
