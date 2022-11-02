package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QIncomeSource;

public class IncomeSourcePredicateBuilder extends BasePredicateBuilder {

    public IncomeSourcePredicateBuilder() {
        super(QIncomeSource.incomeSource.name, QIncomeSource.incomeSource.active);
    }
}
