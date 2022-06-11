package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.persistency.constraint.MaxYear;
import com.onurbcd.eruservice.persistency.constraint.MinYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "name", column = @Column())
public class Budget extends Prime {

    @NotNull
    @Min(1)
    private Short sequence;

    @NotNull
    @MinYear
    @MaxYear
    private Short refYear;

    @NotNull
    @Min(1)
    @Max(12)
    private Short refMonth;

    @NotNull
    @ManyToOne
    private BillType billType;

    @NotNull
    @Column(precision = 19, scale = 4)
    @DecimalMin("0.0001")
    @DecimalMax("999999999999999")
    private BigDecimal amount;

    @NotNull
    private Boolean paid;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
