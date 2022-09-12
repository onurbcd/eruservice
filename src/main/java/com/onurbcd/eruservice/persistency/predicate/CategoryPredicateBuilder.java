package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QCategory;

public class CategoryPredicateBuilder extends BasePredicateBuilder {

    public CategoryPredicateBuilder() {
        super(QCategory.category.name, QCategory.category.active);
    }
}
