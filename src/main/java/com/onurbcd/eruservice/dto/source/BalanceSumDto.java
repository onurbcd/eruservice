package com.onurbcd.eruservice.dto.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BalanceSumDto {

    private BigDecimal partial;

    private BigDecimal total;
}
