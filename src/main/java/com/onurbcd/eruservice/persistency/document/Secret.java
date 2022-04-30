package com.onurbcd.eruservice.persistency.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Secret extends Identifier implements Documentable {

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 5, max = 250)
    private String description;

    @URL(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String link;

    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    private String password;
}
