package com.onurbcd.eruservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class PrimeSaveDto implements Dtoable {

    private String name;

    @Getter(AccessLevel.NONE)
    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }
}
