package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.persistency.entity.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateSourceBalance {

    private Source source;

    private BinaryOperator<BigDecimal> func;

    private BigDecimal value;
}
