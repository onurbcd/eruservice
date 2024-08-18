package com.onurbcd.eruservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PrimeSaveDto implements Dtoable {

    @NotNull
    @Size(min = Constants.SIZE_3, max = Constants.SIZE_50)
    private String name;

    @NotNull
    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }
}
