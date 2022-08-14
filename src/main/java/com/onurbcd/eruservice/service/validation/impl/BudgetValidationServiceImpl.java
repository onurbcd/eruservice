package com.onurbcd.eruservice.service.validation.impl;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.CopyBudgetDto;
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

    @Override
    public void validateCopy(CopyBudgetDto copyBudgetDto) {
        Action.checkIfNotNull(copyBudgetDto.getFrom()).orElseThrow(Error.COPY_BUDGET_FROM_IS_NULL);
        Action.checkIfNotNull(copyBudgetDto.getTo()).orElseThrow(Error.COPY_BUDGET_TO_IS_NULL);
        Action.checkIfNotNull(copyBudgetDto.getFrom().getYear()).orElseThrow(Error.COPY_BUDGET_FROM_YEAR_IS_NULL);
        Action.checkIfNotNull(copyBudgetDto.getFrom().getMonth()).orElseThrow(Error.COPY_BUDGET_FROM_MONTH_IS_NULL);
        Action.checkIfNotNull(copyBudgetDto.getTo().getYear()).orElseThrow(Error.COPY_BUDGET_TO_YEAR_IS_NULL);
        Action.checkIfNotNull(copyBudgetDto.getTo().getMonth()).orElseThrow(Error.COPY_BUDGET_TO_MONTH_IS_NULL);
    }

    private boolean equalRef(BudgetDto budgetDto, @Nullable Budget budget) {
        if (budget == null) {
            return false;
        }

        return DateUtil.equalMonth(budgetDto.getRefYear(), budgetDto.getRefMonth(), budget.getRefYear(),
                budget.getRefMonth());
    }
}
