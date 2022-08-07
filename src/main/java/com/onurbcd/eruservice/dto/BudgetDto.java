package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class BudgetDto extends PrimeDto {

    @Serial
    private static final long serialVersionUID = -362988635811698550L;

    private Short sequence;

    private Short refYear;

    private Short refMonth;

    private BillTypeDto billType;

    private BigDecimal amount;

    private Boolean paid;
}
