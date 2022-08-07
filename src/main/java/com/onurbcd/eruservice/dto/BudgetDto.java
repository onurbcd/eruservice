package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BudgetDto extends PrimeDto {

    @Serial
    private static final long serialVersionUID = -362988635811698550L;

    private Short sequence;

    private Short refYear;

    private Short refMonth;

    private UUID billTypeId;

    private String billTypeName;

    private BigDecimal amount;

    private Boolean paid;
}
