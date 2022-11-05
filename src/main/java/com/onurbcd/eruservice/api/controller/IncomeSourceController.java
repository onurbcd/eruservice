package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.filter.IncomeSourceFilter;
import com.onurbcd.eruservice.dto.incomesource.IncomeSourcePatchDto;
import com.onurbcd.eruservice.dto.incomesource.IncomeSourceSaveDto;
import com.onurbcd.eruservice.service.IncomeSourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income-source")
public class IncomeSourceController
        extends PrimeController<IncomeSourceSaveDto, IncomeSourcePatchDto, IncomeSourceFilter> {

    public IncomeSourceController(IncomeSourceService service) {
        super(service);
    }
}
