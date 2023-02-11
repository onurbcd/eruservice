package com.onurbcd.eruservice.persistency.param;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class SequenceParam {

    private Short year;

    private Short month;

    private Short sequence;

    private Short targetSequence;

    public static SequenceParam of(Short year, Short month) {
        return new SequenceParam(year, month, null, null);
    }

    public static SequenceParam of(Short year, Short month, Short sequence, Short targetSequence) {
        return new SequenceParam(year, month, sequence, targetSequence);
    }
}
