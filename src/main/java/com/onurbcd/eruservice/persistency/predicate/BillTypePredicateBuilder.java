package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QBillType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

public class BillTypePredicateBuilder extends BasePredicateBuilder {

    private BillTypePredicateBuilder() {
    }

    public static BillTypePredicateBuilder of() {
        return new BillTypePredicateBuilder();
    }

    public BillTypePredicateBuilder name(@Nullable String name) {
        if (StringUtils.isNotBlank(name)) {
            builder().and(QBillType.billType.name.containsIgnoreCase(name));
        }

        return this;
    }

    public BillTypePredicateBuilder active(@Nullable Boolean active) {
        if (active != null) {
            builder().and(QBillType.billType.active.eq(active));
        }

        return this;
    }
}
