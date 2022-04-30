package com.onurbcd.eruservice.api.handler;

import com.onurbcd.eruservice.api.enums.Error;
import com.onurbcd.eruservice.api.exception.ApiException;
import com.onurbcd.eruservice.api.resource.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> handleApiException(ApiException e, WebRequest request) {
        return handleExceptionInternal(e, new ApiError(e), new HttpHeaders(), e.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {TransactionSystemException.class})
    protected ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException e,
                                                                      WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof ConstraintViolationException cve) {
            var message = getConstraintViolationExceptionMessage(cve);

            return handleExceptionInternal(e, new ApiError(Error.CONSTRAINT_VIOLATION, message, HttpStatus.CONFLICT),
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

        return controlException(e, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                                           WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof PSQLException psqle) {
            return handleExceptionInternal(e, new ApiError(Error.PSQL, psqle.getMessage(), HttpStatus.CONFLICT),
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

        return controlException(e, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalServerError(Exception e, WebRequest request) {
        return controlException(e, request);
    }

    private ResponseEntity<Object> controlException(Exception e, WebRequest request) {
        logger.error("internal server error", e);

        return handleExceptionInternal(e, new ApiError(Error.INTERNAL_SERVER_ERROR, e.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String getConstraintViolationExceptionMessage(ConstraintViolationException ex) {
        return Optional
                .ofNullable(ex.getConstraintViolations())
                .orElse(Collections.emptySet())
                .stream()
                .map(this::getConstraintViolationMessage)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(", "));
    }

    private <T> String getConstraintViolationMessage(ConstraintViolation<T> cv) {
        return cv == null ? StringUtils.EMPTY : String.format("%s: %s", cv.getPropertyPath(), cv.getMessage());
    }
}
