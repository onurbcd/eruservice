package com.onurbcd.eruservice.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AuditDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6967643188626482657L;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
