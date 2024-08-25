package com.onurbcd.eruservice.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class BudgetFilter extends AbstractFilterable {

    private Short refYear;

    private Short refMonth;

    private UUID billTypeId;

    private Boolean paid;
}
