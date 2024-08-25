package com.onurbcd.eruservice.dto.incomesource;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class IncomeSourcePatchDto extends PrimePatchDto {

    public static IncomeSourcePatchDto of(Boolean active) {
        return IncomeSourcePatchDto
                .builder()
                .active(active)
                .build();
    }
}
