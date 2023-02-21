package com.onurbcd.eruservice.dto.balance;

import com.onurbcd.eruservice.dto.PrimeDto;
import com.onurbcd.eruservice.dto.enums.BalanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BalanceDto extends PrimeDto {

    private Short sequence;

    private Integer dayId;

    private LocalDate dayCalendarDate;

    private UUID sourceId;

    private String sourceName;

    private UUID categoryId;

    private String categoryName;

    private BigDecimal amount;

    private String code;

    private String description;

    private BalanceType balanceType;

    private Set<UUID> documentsIds;
}
