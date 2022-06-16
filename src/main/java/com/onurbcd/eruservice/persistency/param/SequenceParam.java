package com.onurbcd.eruservice.persistency.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SequenceParam {

    private Short year;

    private Short month;

    public SequenceParam(Short year, Short month) {
        this.year = year;
        this.month = month;
    }
}
