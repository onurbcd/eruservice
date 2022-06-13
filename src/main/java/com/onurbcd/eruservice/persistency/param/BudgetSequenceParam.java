package com.onurbcd.eruservice.persistency.param;

import com.onurbcd.eruservice.persistency.entity.Budget;

public class BudgetSequenceParam extends AbstractSequenceParam {

    public BudgetSequenceParam(Budget budget) {
        super(budget.getRefYear(), budget.getRefMonth());
    }
}
