package com.onurbcd.eruservice.dto.budget;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.persistency.constraint.MaxYear;
import com.onurbcd.eruservice.persistency.constraint.MinYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
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
    @DecimalMin(Constants.BUDGET_AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal amount;

    private Boolean paid;
}
