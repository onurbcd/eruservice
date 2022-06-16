package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;

public interface SequenceValidationService<T extends SequenceRepository> {

    void validate(SequenceParam currentParam, SequenceParam targetParam, Direction direction);
}
