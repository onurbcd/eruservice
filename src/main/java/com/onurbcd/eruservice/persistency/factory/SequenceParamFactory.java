package com.onurbcd.eruservice.persistency.factory;

import com.onurbcd.eruservice.persistency.entity.SequenceEntity;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SequenceParamFactory {

    public static SequenceParam createNext(SequenceEntity sequenceEntity) {
        return SequenceParam.of(sequenceEntity.getSequenceYear(), sequenceEntity.getSequenceMonth());
    }

    public static SequenceParam create(LocalDate calendarDate) {
        return SequenceParam.of((short) calendarDate.getYear(), (short) calendarDate.getMonth().getValue());
    }

    public static SequenceParam create(SequenceEntity sequenceEntity) {
        return create(sequenceEntity, null);
    }

    public static SequenceParam create(SequenceEntity sequenceEntity, Short targetSequence) {
        return SequenceParam.of(sequenceEntity.getSequenceYear(), sequenceEntity.getSequenceMonth(),
                sequenceEntity.getSequenceValue(), targetSequence);
    }
}
