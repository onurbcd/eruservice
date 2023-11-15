package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.dto.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Secret extends Prime {

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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
