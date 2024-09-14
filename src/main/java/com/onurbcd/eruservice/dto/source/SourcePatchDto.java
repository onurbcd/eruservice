package com.onurbcd.eruservice.dto.source;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SourcePatchDto extends PrimePatchDto {

    public static SourcePatchDto of(Boolean active) {
        return SourcePatchDto
                .builder()
                .active(active)
                .build();
    }
}
