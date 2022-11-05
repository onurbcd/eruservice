package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PrimePatchDto implements Dtoable {

    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }
}
