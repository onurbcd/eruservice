package com.onurbcd.eruservice.dto.secret;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.dto.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class SecretSaveDto extends PrimeSaveDto {

    @Size(min = Constants.SIZE_5, max = Constants.SIZE_250)
    private String description;

    @URL(regexp = Constants.REGEXP_URL)
    @Size(min = Constants.SIZE_7, max = Constants.SIZE_2048)
    private String link;

    @NotNull
    @Size(min = Constants.SIZE_3, max = Constants.SIZE_50)
    private String username;

    @NotNull
    private String password;
}
