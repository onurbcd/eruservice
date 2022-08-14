package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.CopyBudgetDto;
import com.onurbcd.eruservice.persistency.entity.Budget;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface BudgetValidationService {

    void validate(BudgetDto budgetDto, @Nullable Budget budget, @Nullable UUID id);

    void validateCopy(CopyBudgetDto copyBudgetDto);
}
