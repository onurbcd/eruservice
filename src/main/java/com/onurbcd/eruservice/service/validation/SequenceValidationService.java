package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;

public interface SequenceValidationService {

    void validate(SequenceParam currentParam, SequenceParam targetParam, Direction direction);

    void validateSwapPosition(SequenceParam param);
}
