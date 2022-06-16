package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;

import java.util.UUID;

public interface SequenceService<T extends SequenceRepository> {

    Short getNextSequence(SequenceParam sequenceParam);

    void changeSequence(UUID id, Direction direction);
}
