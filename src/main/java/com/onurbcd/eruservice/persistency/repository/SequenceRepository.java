package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.param.Sequenceable;
import org.springframework.data.repository.query.Param;

public interface SequenceRepository {

    Short getMaxSequence(@Param("sequenceable") Sequenceable sequenceable);
}
