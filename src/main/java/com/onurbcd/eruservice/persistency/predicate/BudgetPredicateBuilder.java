package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.dto.RefDto;
import com.onurbcd.eruservice.persistency.entity.QBudget;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;

import java.util.UUID;

public class BudgetPredicateBuilder extends BasePredicateBuilder {

    public BudgetPredicateBuilder() {
        super(QBudget.budget.name, QBudget.budget.active);
    }

    public static BudgetPredicateBuilder of() {
        return new BudgetPredicateBuilder();
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

    public BudgetPredicateBuilder id(UUID id) {
        builder().and(QBudget.budget.id.eq(id));
        return this;
    }

    public static Predicate ref(RefDto refDto) {
        return BudgetPredicateBuilder.of().refYear(refDto.getYear()).refMonth(refDto.getMonth()).build();
    }
}
