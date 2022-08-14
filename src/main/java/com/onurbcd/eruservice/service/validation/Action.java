package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import com.onurbcd.eruservice.util.CollectionUtil;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;

public class Action {

    private final boolean checkConditionNotTrue;

    private Action(boolean checkConditionNotTrue) {
        this.checkConditionNotTrue = checkConditionNotTrue;
    }

    public void orElseThrow(Error error, Object... args) {
        if (checkConditionNotTrue) {
            throw new ApiException(error, HttpStatus.CONFLICT, args);
        }
    }

    public void orElseThrowNotFound(Object... args) {
        if (checkConditionNotTrue) {
            throw new ApiException(Error.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND, args);
        }
    }

    public static Action checkIf(boolean checkCondition) {
        return new Action(!checkCondition);
    }

    public static Action checkIfNot(boolean checkCondition) {
        return new Action(checkCondition);
    }

    public static Action checkIfSizeBetween(String value, int min, int max) {
        return new Action(!(value == null || (value.length() >= min && value.length() <= max)));
    }

    public static <T> Action checkIfNotNull(T value) {
        return new Action(value == null);
    }

    public static <T> Action checkIfNotEmpty(Collection<T> collection) {
        return new Action(CollectionUtil.isEmpty(collection));
    }
}
