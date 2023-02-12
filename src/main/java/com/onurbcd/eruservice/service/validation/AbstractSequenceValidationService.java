package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.repository.SequenceRepository;
import com.onurbcd.eruservice.service.enums.Error;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;

@RequiredArgsConstructor
public abstract class AbstractSequenceValidationService implements SequenceValidationService {

    private final SequenceRepository repository;

    @Override
    public void validate(SequenceParam currentParam, SequenceParam targetParam, Direction direction) {
        Action.checkIfNot(Direction.UP.equals(direction) && currentParam.getSequence().equals((short) 1))
                .orElseThrow(Error.WRONG_DIRETION_UP);

        var existsTargetSequence = BooleanUtils.isTrue(repository.existsSequence(targetParam));

        Action.checkIfNot(Direction.DOWN.equals(direction) && !existsTargetSequence)
                .orElseThrow(Error.WRONG_DIRETION_DOWN);

        Action.checkIf(existsTargetSequence).orElseThrowNotFound("swap sequence target");
    }

    @Override
    public void validateSwapPosition(SequenceParam param) {
        Action.checkIfNot(param.getSequence().equals(param.getTargetSequence())).orElseThrow(Error.SWAP_SAME_POSITION);
        Action.checkIfNot(param.getTargetSequence().compareTo((short) 1) < 0).orElseThrow(Error.WRONG_DIRETION_DOWN);
        var maxSequence = repository.getMaxSequence(param);
        Action.checkIfNot(param.getTargetSequence().compareTo(maxSequence) > 0).orElseThrow(Error.WRONG_DIRETION_UP);
    }
}
