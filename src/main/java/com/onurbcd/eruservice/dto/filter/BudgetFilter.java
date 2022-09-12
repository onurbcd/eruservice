package com.onurbcd.eruservice.dto.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BudgetFilter extends AbstractFilterable {

    private Short refYear;

    private Short refMonth;

    private UUID billTypeId;

    private Boolean paid;
}
