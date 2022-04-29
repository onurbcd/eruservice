package com.onurbcd.eruservice.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class SecretDto extends IdentifierDto implements Dtoable, Serializable {

    @Serial
    private static final long serialVersionUID = -3994423959939585870L;

    private String name;

    private String description;

    private String link;

    private String username;

    private String password;
}
