package com.onurbcd.eruservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PrimeSaveDto implements Dtoable {

    private String name;
    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }
}
