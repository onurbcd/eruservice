package com.onurbcd.eruservice.dto.billtype;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BillTypePatchDto extends PrimePatchDto {

    public static BillTypePatchDto of(Boolean active) {
        return BillTypePatchDto
                .builder()
                .active(active)
                .build();
    }
}
