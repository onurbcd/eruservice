package com.onurbcd.eruservice.persistency.entity;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.enums.CurrencyType;
import com.onurbcd.eruservice.dto.enums.SourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "source")
@NoArgsConstructor
@Getter
@Setter
public class Source extends Prime {

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "income_source_id", nullable = false)
    private IncomeSource incomeSource;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false, length = 8)
    private SourceType sourceType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", nullable = false, length = 7)
    private CurrencyType currencyType;

    @NotNull
    @Column(name = "balance", nullable = false, precision = 19, scale = 4)
    @DecimalMin(Constants.AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
