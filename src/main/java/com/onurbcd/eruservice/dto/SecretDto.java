package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor
@Getter
@Setter
public class SecretDto extends PrimeDto {

    @Serial
    private static final long serialVersionUID = -3994423959939585870L;

    private String description;

    private String link;

    private String username;

    private String password;
}
