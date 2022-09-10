package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.CopyBudgetDto;
import com.onurbcd.eruservice.dto.SumDto;
import com.onurbcd.eruservice.service.filter.BudgetFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface BudgetService extends CrudService, Sequenceable {

    Set<SumDto> getSumByMonth(BudgetFilter filter);

    void copy(CopyBudgetDto copyBudgetDto);

    @Transactional
    void deleteAll(Short refYear, Short refMonth);
}
