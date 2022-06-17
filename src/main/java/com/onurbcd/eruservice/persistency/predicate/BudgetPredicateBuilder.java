package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QBudget;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.UUID;

public class BudgetPredicateBuilder extends BasePredicateBuilder {

    private BudgetPredicateBuilder() {
    }

    public static BudgetPredicateBuilder of() {
        return new BudgetPredicateBuilder();
    }

    public BudgetPredicateBuilder name(@Nullable String name) {
        if (StringUtils.isNotBlank(name)) {
            builder().and(QBudget.budget.name.containsIgnoreCase(name));
        }

        return this;
    }

    public BudgetPredicateBuilder active(@Nullable Boolean active) {
        if (active != null) {
            builder().and(QBudget.budget.active.eq(active));
        }

        return this;
    }

    public BudgetPredicateBuilder refYear(@Nullable Short refYear) {
        if (refYear != null) {
            builder().and(QBudget.budget.refYear.eq(refYear));
        }

        return this;
    }

    public BudgetPredicateBuilder refMonth(@Nullable Short refMonth) {
        if (refMonth != null) {
            builder().and(QBudget.budget.refMonth.eq(refMonth));
        }

        return this;
    }

    public BudgetPredicateBuilder billTypeId(@Nullable UUID billTypeId) {
        if (billTypeId != null) {
            builder().and(QBudget.budget.billType.id.eq(billTypeId));
        }

        return this;
    }

    public BudgetPredicateBuilder paid(@Nullable Boolean paid) {
        if (paid != null) {
            builder().and(QBudget.budget.paid.eq(paid));
        }

        return this;
    }
}
