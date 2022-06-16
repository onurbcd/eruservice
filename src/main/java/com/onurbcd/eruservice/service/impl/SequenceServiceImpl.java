package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;
import com.onurbcd.eruservice.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SequenceServiceImpl<T extends SequenceRepository> implements SequenceService<T> {

    private final T repository;

    @Autowired
    public SequenceServiceImpl(T repository) {
        this.repository = repository;
    }

    @Override
    public Short getNextSequence(SequenceParam sequenceParam) {
        var maxSequence = repository.getMaxSequence(sequenceParam);
        return maxSequence == null ? 1 : (short) (maxSequence + 1);
    }

    @Override
    public void changeSequence(UUID id, Direction direction) {

    }
}
