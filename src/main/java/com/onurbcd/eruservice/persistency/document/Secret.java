package com.onurbcd.eruservice.persistency.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collation = "{ locale: 'pt', strength: 2 }")
@NoArgsConstructor
@Getter
@Setter
public class Secret extends Identifier implements Documentable {

    @Indexed(unique = true)
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
