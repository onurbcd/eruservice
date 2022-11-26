package com.onurbcd.eruservice.api.handler;

import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import com.onurbcd.eruservice.service.resource.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(ApiException e, WebRequest request) {
        return handleExceptionInternal(e, new ApiError(e), new HttpHeaders(), e.getHttpStatus(), request);
    }

    @ExceptionHandler(TransactionSystemException.class)
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                                           WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof PSQLException psqle) {
            return handleExceptionInternal(e, new ApiError(Error.PSQL, psqle.getMessage(), HttpStatus.CONFLICT),
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

        return controlException(e, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e,
                                                                        WebRequest request) {

        var errors = getConstraintViolationExceptionMessages(e);
        var apiError = new ApiError(Error.BAD_REQUEST, HttpStatus.BAD_REQUEST, errors);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalServerError(Exception e, WebRequest request) {
        return controlException(e, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {

        var errors = new ArrayList<String>();

        for (var error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (var error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        var apiError = new ApiError(Error.BAD_REQUEST, status, errors);
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ResponseEntity<Object> controlException(Exception e, WebRequest request) {
        logger.error("internal server error", e);
        var rootCause = ExceptionUtils.getRootCause(e);

        return handleExceptionInternal(rootCause != null ? (Exception) rootCause : e,
                new ApiError(Error.INTERNAL_SERVER_ERROR, rootCause != null ? rootCause.toString() : e.toString(),
                        HttpStatus.INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String getConstraintViolationExceptionMessage(ConstraintViolationException e) {
        return Optional
                .ofNullable(e.getConstraintViolations())
                .orElse(Collections.emptySet())
                .stream()
                .map(this::getConstraintViolationMessage)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(", "));
    }

    protected List<String> getConstraintViolationExceptionMessages(ConstraintViolationException e) {
        return Optional
                .ofNullable(e.getConstraintViolations())
                .orElse(Collections.emptySet())
                .stream()
                .map(this::getConstraintViolationMessage)
                .filter(StringUtils::isNotBlank)
                .toList();
    }

    private <T> String getConstraintViolationMessage(ConstraintViolation<T> cv) {
        return cv == null ? StringUtils.EMPTY : String.format("%s: %s", cv.getPropertyPath(), cv.getMessage());
    }
}
