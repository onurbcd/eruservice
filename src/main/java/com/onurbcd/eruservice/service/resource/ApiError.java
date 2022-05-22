package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ApiError implements Serializable {

    @Serial
    private static final long serialVersionUID = 2077701515122453040L;

    private String name;

    private String message;

    private int code;

    public ApiError(String name, String message, int code) {
        this.name = name;
        this.message = message;
        this.code = code;
    }

    public ApiError(ApiException e) {
        this(e.getError().name(), e.getMessage(), e.getHttpStatus().value());
    }

    public ApiError(Error error, String message, HttpStatus httpStatus) {
        this(error.name(), message, httpStatus.value());
    }
}
