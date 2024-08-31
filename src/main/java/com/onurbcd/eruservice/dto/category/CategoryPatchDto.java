package com.onurbcd.eruservice.dto.category;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CategoryPatchDto extends PrimePatchDto {

    public static CategoryPatchDto of(Boolean active) {
        return CategoryPatchDto
                .builder()
                .active(active)
                .build();
    }
}
