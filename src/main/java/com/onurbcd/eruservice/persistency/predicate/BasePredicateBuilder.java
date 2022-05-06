package com.onurbcd.eruservice.persistency.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public abstract class BasePredicateBuilder {

    private final BooleanBuilder builder = new BooleanBuilder();

    protected BooleanBuilder builder() {
        return builder;
    }

    public Predicate build() {
        return builder;
    }
}
