package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.validation.constraint.MaxYear;
import com.onurbcd.eruservice.validation.constraint.MinYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "name", column = @Column())
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name", "refYear", "refMonth"
        }),
        @UniqueConstraint(columnNames = {
                "sequence", "refYear", "refMonth"
        })
})
public class Budget extends Prime implements SequenceEntity {

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
    @ManyToOne(fetch = FetchType.LAZY)
    private BillType billType;

    @NotNull
    @Column(precision = 19, scale = 4)
    @DecimalMin(Constants.POSITIVE_AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal amount;

    @NotNull
    private Boolean paid;

    @Override
    public Short getSequenceYear() {
        return refYear;
    }

    @Override
    public Short getSequenceMonth() {
        return refMonth;
    }

    @Override
    public Short getSequenceValue() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
