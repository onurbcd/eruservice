package com.onurbcd.eruservice.persistency.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends Prime {

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
