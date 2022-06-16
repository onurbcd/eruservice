package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;

public interface SequenceService<T extends SequenceRepository> {

    Short getNextSequence(SequenceParam sequenceParam);

    void swapSequence(SequenceParam currentParam, Direction direction);
}
