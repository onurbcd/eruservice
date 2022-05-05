package com.onurbcd.eruservice.api.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR("Internal Server Error"),

    CRYPTO("Crypto"),

    CONSTRAINT_VIOLATION("Constraint Violation"),

    PSQL("PSQL Exception"),

    ENTITY_DOES_NOT_EXIST("Entity with id '%s' does not exist in the database"),

    SIZE_NOT_BETWEEN("%s: size must be between %d and %d");

    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
