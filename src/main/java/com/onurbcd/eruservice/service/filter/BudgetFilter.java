package com.onurbcd.eruservice.service.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BudgetFilter extends AbstractFilterable implements Serializable {

    @Serial
    private static final long serialVersionUID = -605941962275216278L;

    private Short refYear;

    private Short refMonth;

    private UUID billTypeId;

    private Boolean paid;
}
