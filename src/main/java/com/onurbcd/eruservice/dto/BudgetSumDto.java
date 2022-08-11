package com.onurbcd.eruservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BudgetSumDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8813036559937890225L;

    private BigDecimal amount;

    private Boolean paid;

    private Long size;
}
