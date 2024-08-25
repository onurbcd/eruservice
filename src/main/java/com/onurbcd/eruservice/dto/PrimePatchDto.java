package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class PrimePatchDto implements Dtoable {

    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }
}
