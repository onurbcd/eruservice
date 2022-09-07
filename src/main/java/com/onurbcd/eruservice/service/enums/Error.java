package com.onurbcd.eruservice.service.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR("Internal Server Error"),

    CRYPTO("Crypto"),

    CONSTRAINT_VIOLATION("Constraint Violation"),

    PSQL("PSQL Exception"),

    ENTITY_DOES_NOT_EXIST("Entity with id '%s' does not exist in the database"),

    SIZE_NOT_BETWEEN("%s: size must be between %d and %d"),

    SEQUENCE_CHANGED("the sequence cannot be changed by a PUT request; use PATCH instead; current value: '%d'; new value: '%d'"),

    REFERENCE_CHANGED("reference year and month cannot be changed"),

    WRONG_DIRETION_UP("The sequence cannot get any lower"),

    WRONG_DIRETION_DOWN("The sequence cannot get any higher"),

    BUDGET_REF_YEAR_IS_NULL("Reference year is mandatory"),

    BUDGET_REF_MONTH_IS_NULL("Reference month is mandatory"),

    COPY_BUDGET_FROM_IS_NULL("Copy budget from is mandatory"),

    COPY_BUDGET_TO_IS_NULL("Copy budget to is mandatory"),

    COPY_BUDGET_FROM_YEAR_IS_NULL("Copy budget From Year is mandatory"),

    COPY_BUDGET_FROM_MONTH_IS_NULL("Copy budget From Month is mandatory"),

    COPY_BUDGET_TO_YEAR_IS_NULL("Copy budget To Year is mandatory"),

    COPY_BUDGET_TO_MONTH_IS_NULL("Copy budget To Month is mandatory"),

    COPY_BUDGET_FROM_IS_EMPTY("There is no budget for the source month %02d/%d"),

    COPY_BUDGET_TO_ALREADY_EXISTS("There is already a budget for the target month %02d/%d"),

    COPY_BUDGET_EQUAL_MONTH("Origin and destination months cannot be the same"),

    SWAP_SAME_POSITION("The current and target positions are the same")

    ;

    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
