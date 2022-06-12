package com.onurbcd.eruservice.service.validation.impl;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.BudgetValidationService;
import com.onurbcd.eruservice.util.DateUtil;
import com.onurbcd.eruservice.util.NumberUtil;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BudgetValidationServiceImpl implements BudgetValidationService {

    @Override
    public void validate(BudgetDto budgetDto, @Nullable Budget budget, @Nullable UUID id) {
        Action.checkIf(id == null || budget != null).orElseThrowNotFound(id);
        var sequence = budget != null ? budget.getSequence() : null;

        Action.checkIf(id == null || NumberUtil.equals(budgetDto.getSequence(), sequence))
                .orElseThrow(Error.SEQUENCE_CHANGED, sequence, budgetDto.getSequence());

        Action.checkIf(id == null || equalRef(budgetDto, budget)).orElseThrow(Error.REFERENCE_CHANGED);
    }

    private boolean equalRef(BudgetDto budgetDto, @Nullable Budget budget) {
        if (budget == null) {
            return false;
        }

        return DateUtil.equalMonth(budgetDto.getRefYear(), budgetDto.getRefMonth(), budget.getRefYear(),
                budget.getRefMonth());
    }
}
