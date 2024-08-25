package com.onurbcd.eruservice.dto.filter;

import com.onurbcd.eruservice.dto.enums.CurrencyType;
import com.onurbcd.eruservice.dto.enums.SourceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class SourceFilter extends AbstractFilterable {

    private UUID incomeSourceId;

    private SourceType sourceType;

    private CurrencyType currencyType;
}
