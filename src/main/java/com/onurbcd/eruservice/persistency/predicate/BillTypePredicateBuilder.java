package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QBillType;

public class BillTypePredicateBuilder extends BasePredicateBuilder {

    public BillTypePredicateBuilder() {
        super(QBillType.billType.name, QBillType.billType.active);
    }
}
