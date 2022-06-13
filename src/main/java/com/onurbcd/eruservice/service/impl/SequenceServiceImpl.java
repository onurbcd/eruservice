package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.persistency.param.Sequenceable;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;
import com.onurbcd.eruservice.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceServiceImpl<T extends SequenceRepository> implements SequenceService<T> {

    private final T repository;

    @Autowired
    public SequenceServiceImpl(T repository) {
        this.repository = repository;
    }

    @Override
    public Short getNextSequence(Sequenceable sequenceable) {
        var maxSequence = repository.getMaxSequence(sequenceable);
        return maxSequence == null ? 1 : (short) (maxSequence + 1);
    }
}
