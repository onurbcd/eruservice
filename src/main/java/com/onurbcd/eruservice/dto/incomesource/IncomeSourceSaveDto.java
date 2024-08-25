package com.onurbcd.eruservice.dto.incomesource;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class IncomeSourceSaveDto extends PrimeSaveDto {

    public static IncomeSourceSaveDto of(String name, Boolean active) {
        return IncomeSourceSaveDto
                .builder()
                .name(name)
                .active(active)
                .build();
    }
}
