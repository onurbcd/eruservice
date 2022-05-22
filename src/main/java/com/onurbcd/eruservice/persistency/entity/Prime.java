package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.persistency.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@EqualsAndHashCode(of = "id", callSuper = false)
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
    private boolean active;

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
