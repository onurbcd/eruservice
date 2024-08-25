package com.onurbcd.eruservice.dto.budget;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.validation.constraint.MaxYear;
import com.onurbcd.eruservice.validation.constraint.MinYear;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class BudgetSaveDto extends PrimeSaveDto {

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
    private UUID billTypeId;

    @NotNull
    @DecimalMin(Constants.POSITIVE_AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal amount;

    private Boolean paid;
}
