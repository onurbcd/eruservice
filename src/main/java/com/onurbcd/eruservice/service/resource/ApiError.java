package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ApiError implements Serializable {

    @Serial
    private static final long serialVersionUID = 2077701515122453040L;

    private String name;

    private String message;

    private int code;

    private List<String> errors;

    public ApiError(String name, String message, int code, List<String> errors) {
        this.name = name;
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public ApiError(ApiException e) {
        this(e.getError().name(), e.getMessage(), e.getHttpStatus().value(), null);
    }

    public ApiError(Error error, String message, HttpStatus httpStatus) {
        this(error.name(), message, httpStatus.value(), null);
    }

    public ApiError(Error error, HttpStatus httpStatus, List<String> errors) {
        this(error.name(), error.format(), httpStatus.value(), errors);
    }
}
