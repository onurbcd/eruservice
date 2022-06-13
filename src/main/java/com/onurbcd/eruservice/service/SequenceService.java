package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.persistency.param.Sequenceable;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;

public interface SequenceService<T extends SequenceRepository> {

    Short getNextSequence(Sequenceable sequenceable);
}
