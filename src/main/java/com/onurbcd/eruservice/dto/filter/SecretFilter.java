package com.onurbcd.eruservice.dto.filter;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class SecretFilter extends AbstractFilterable {

    public static SecretFilter of(Boolean active, String search) {
        return SecretFilter
                .builder()
                .active(active)
                .search(search)
                .build();
    }
}
