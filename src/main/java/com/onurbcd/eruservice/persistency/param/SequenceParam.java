package com.onurbcd.eruservice.persistency.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SequenceParam {

    private Short year;

    private Short month;

    private Short sequence;

    private Short targetSequence;

    public SequenceParam(Short year, Short month) {
        this.year = year;
        this.month = month;
    }

    public SequenceParam(Short year, Short month, Short sequence) {
        this(year, month);
        this.sequence = sequence;
    }

    public SequenceParam(Short year, Short month, Short sequence, Short targetSequence) {
        this(year, month, sequence);
        this.targetSequence = targetSequence;
    }
}
