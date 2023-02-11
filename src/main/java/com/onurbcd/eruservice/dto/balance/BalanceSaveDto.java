package com.onurbcd.eruservice.dto.balance;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.dto.enums.BalanceType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BalanceSaveDto extends PrimeSaveDto {

    @NotNull
    @Min(1)
    private Short sequence;

    @NotNull
    private LocalDate dayCalendarDate;

    @NotNull
    private UUID sourceId;

    @NotNull
    private UUID categoryId;

    @NotNull
    @DecimalMin(Constants.AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal amount;

    @NotNull
    @Size(max = Constants.SIZE_150)
    private String code;

    @Size(max = Constants.SIZE_250)
    private String description;

    @NotNull
    private BalanceType balanceType;
}
