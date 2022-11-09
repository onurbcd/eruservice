package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.dto.filter.IncomeSourceFilter;
import com.onurbcd.eruservice.dto.incomesource.IncomeSourcePatchDto;
import com.onurbcd.eruservice.dto.incomesource.IncomeSourceSaveDto;
import com.onurbcd.eruservice.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income-source")
public class IncomeSourceController
        extends PrimeController<IncomeSourceSaveDto, IncomeSourcePatchDto, IncomeSourceFilter> {

    public IncomeSourceController(@PrimeService(Domain.INCOME_SOURCE) CrudService service) {
        super(service);
    }
}
