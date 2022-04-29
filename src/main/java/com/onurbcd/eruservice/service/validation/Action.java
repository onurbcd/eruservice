package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.api.enums.Error;
import com.onurbcd.eruservice.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class Action {

    private final boolean checkConditionNotTrue;

    private Action(boolean checkConditionNotTrue) {
        this.checkConditionNotTrue = checkConditionNotTrue;
    }

    public Action orElseThrow(Error error, Object... args) {
        if (checkConditionNotTrue) {
            throw new ApiException(error, HttpStatus.CONFLICT, args);
        }

        return this;
    }

    public Action orElseThrowNotFound(Object... args) {
        if (checkConditionNotTrue) {
            throw new ApiException(Error.DOCUMENT_DOES_NOT_EXIST, HttpStatus.NOT_FOUND, args);
        }

        return this;
    }

    public static Action checkIf(boolean checkCondition) {
        return new Action(!checkCondition);
    }

    public static Action checkIfSizeGE(String value, int size) {
        return new Action(!(value == null || value.length() >= size));
    }

    public static <T> Action checkIfNotNull(T value) {
        return new Action(value == null);
    }
}
