package com.onurbcd.eruservice.dto.filter;

import com.onurbcd.eruservice.dto.enums.CurrencyType;
import com.onurbcd.eruservice.dto.enums.SourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class SourceFilter extends AbstractFilterable {

    private UUID incomeSourceId;

    private SourceType sourceType;

    private CurrencyType currencyType;
}
