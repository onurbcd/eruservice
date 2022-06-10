package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.filter.BudgetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onurbcd.eruservice.api.ApiConstants.PATH_BUDGET;

@RestController
@RequestMapping(PATH_BUDGET)
public class BudgetController extends EruController<BudgetDto, BudgetFilter> {

    @Autowired
    public BudgetController(BudgetService service) {
        super(service);
    }
}
