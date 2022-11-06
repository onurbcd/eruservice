package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.budget.BudgetDto;
import com.onurbcd.eruservice.dto.budget.BudgetSaveDto;
import com.onurbcd.eruservice.dto.budget.CopyBudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface BudgetValidationService {

    void validate(BudgetSaveDto budgetSaveDto, @Nullable Budget budget, @Nullable UUID id);

    List<BudgetDto> validateCopy(CopyBudgetDto copyBudgetDto);
}
