package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.dto.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BillType extends Prime {

    @NotNull
    @Size(max = Constants.SIZE_250)
    @Column(name = "path", nullable = false, length = Constants.SIZE_250)
    private String path;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
