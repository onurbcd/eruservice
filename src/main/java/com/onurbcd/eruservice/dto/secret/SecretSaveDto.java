package com.onurbcd.eruservice.dto.secret;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class SecretSaveDto extends PrimeSaveDto {

    private String description;
    private String link;
    private String username;
    private String password;
}
