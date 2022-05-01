package com.onurbcd.eruservice.persistency.document;

import com.onurbcd.eruservice.service.validation.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Secret extends Prime {

    @Size(min = Constants.SIZE_5, max = Constants.SIZE_250)
    private String description;

    @URL(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    @Size(min = Constants.SIZE_7, max = Constants.SIZE_2048)
    private String link;

    @NotNull
    @Size(min = Constants.SIZE_3, max = Constants.SIZE_50)
    private String username;

    @NotNull
    private String password;
}
