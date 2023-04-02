package com.onurbcd.eruservice.dto.filter;

import com.onurbcd.eruservice.dto.enums.BalanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BalanceFilter extends AbstractFilterable {

    private LocalDate dayCalendarDate;

    private UUID sourceId;

    private UUID categoryId;

    private BalanceType balanceType;

    private Short dayCalendarYear;

    private Short dayCalendarMonth;

    private Short dayCalendarDayInMonth;
}
