package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryPredicateBuilder extends BasePredicateBuilder {

    public static CategoryPredicateBuilder of() {
        return new CategoryPredicateBuilder();
    }

    public CategoryPredicateBuilder name(@Nullable String name) {
        if (StringUtils.isNotBlank(name)) {
            builder().and(QCategory.category.name.containsIgnoreCase(name));
        }

        return this;
    }

    public CategoryPredicateBuilder active(@Nullable Boolean active) {
        if (active != null) {
            builder().and(QCategory.category.active.eq(active));
        }

        return this;
    }
}
