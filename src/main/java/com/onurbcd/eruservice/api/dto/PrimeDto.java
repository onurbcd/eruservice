package com.onurbcd.eruservice.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PrimeDto extends AuditDto implements Dtoable {

    @Serial
    private static final long serialVersionUID = 4354706843093085390L;

    private UUID id;

    private String name;

    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }
}
