package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.persistency.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Prime extends Audit implements Entityable {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(unique = true)
    @NotNull
    @Size(min = Constants.SIZE_3, max = Constants.SIZE_50)
    private String name;

    @NotNull
    private Boolean active;

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Prime prime = (Prime) o;

        return new EqualsBuilder().append(id, prime.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
