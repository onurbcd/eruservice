package com.onurbcd.eruservice.api.handler;

import com.onurbcd.eruservice.api.enums.Error;
import com.onurbcd.eruservice.api.exception.ApiException;
import com.onurbcd.eruservice.api.resource.ApiError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> handleApiException(ApiException e, WebRequest request) {
        return handleExceptionInternal(e, new ApiError(e), new HttpHeaders(), e.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e,
                                                                        WebRequest request) {

        return handleExceptionInternal(e, new ApiError(Error.CONSTRAINT_VIOLATION, e.getMessage(), HttpStatus.CONFLICT),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    protected ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e, WebRequest request) {
        return handleExceptionInternal(e, new ApiError(Error.DUPLICATE_KEY, e.getMessage(), HttpStatus.CONFLICT),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalServerError(Exception e, WebRequest request) {
        logger.error("internal server error", e);

        return handleExceptionInternal(e, new ApiError(Error.INTERNAL_SERVER_ERROR, e.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
