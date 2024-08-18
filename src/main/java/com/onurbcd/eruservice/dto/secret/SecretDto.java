package com.onurbcd.eruservice.dto.secret;

import com.onurbcd.eruservice.dto.PrimeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SecretDto extends PrimeDto {

    private String description;
    private String link;
    private String username;
    private String password;
}
