package com.onurbcd.eruservice.persistency.factory;

import com.onurbcd.eruservice.TestFactory;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.onurbcd.eruservice.TestConstants.MONTH;
import static com.onurbcd.eruservice.TestConstants.SEQUENCE;
import static com.onurbcd.eruservice.TestConstants.YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SequenceParamFactoryTest {

    @Test
    void givenSequenceEntity_shouldCreateNextSequenceParam() {
        var budget = TestFactory.createBudget();

        var sequenceParam = SequenceParamFactory.createNext(budget);

        assertSequenceParam(sequenceParam, null, null);
    }

    @Test
    void givenLocalDate_shouldCreateSequenceParam() {
        var calendarDate = LocalDate.of(YEAR, MONTH, 1);

        var sequenceParam = SequenceParamFactory.create(calendarDate);

        assertSequenceParam(sequenceParam, null, null);
    }

    @Test
    void givenSequenceEntity_shouldCreateSequenceParam() {
        var budget = TestFactory.createBudget();

        var sequenceParam = SequenceParamFactory.create(budget);

        assertSequenceParam(sequenceParam, SEQUENCE, null);
    }

    @Test
    void givenSequenceEntityAndTargetSequence_shouldCreateSequenceParam() {
        var budget = TestFactory.createBudget();
        var targetSequence = Short.valueOf("2");

        var sequenceParam = SequenceParamFactory.create(budget, targetSequence);

        assertSequenceParam(sequenceParam, SEQUENCE, targetSequence);
    }

    private void assertSequenceParam(SequenceParam sequenceParam, Short sequence, Short targetSequence) {
        assertNotNull(sequenceParam);
        assertEquals(YEAR, sequenceParam.getYear());
        assertEquals(MONTH, sequenceParam.getMonth());
        assertEquals(sequence, sequenceParam.getSequence());
        assertEquals(targetSequence, sequenceParam.getTargetSequence());
        assertNull(sequenceParam.getBalanceType());
    }
}
