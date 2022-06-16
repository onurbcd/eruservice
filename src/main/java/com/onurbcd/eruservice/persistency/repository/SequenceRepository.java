package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.param.SequenceParam;
import org.springframework.data.repository.query.Param;

public interface SequenceRepository {

    Short getMaxSequence(@Param("sequenceParam") SequenceParam sequenceParam);
}
