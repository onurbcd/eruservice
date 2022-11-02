package com.onurbcd.eruservice.dto;

import com.onurbcd.eruservice.persistency.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
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
