package com.onurbcd.eruservice.dto.secret;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SecretSaveDto extends PrimeSaveDto {

    private String description;
    private String link;
    private String username;
    private String password;
}
