package com.onurbcd.eruservice.api.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR("Internal Server Error"),

    CRYPTO("Crypto"),

    CONSTRAINT_VIOLATION("Constraint Violation"),

    DUPLICATE_KEY("Duplicate Key"),

    DOCUMENT_DOES_NOT_EXIST("Document with id '%s' does not exist in the database"),

    SIZE_LESS_THAN("%s: size must be greater or equal to %d");

    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
