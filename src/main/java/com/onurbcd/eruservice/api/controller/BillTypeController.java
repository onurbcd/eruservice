package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.filter.BillTypeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onurbcd.eruservice.api.ApiConstants.PATH_BILL_TYPE;

@RestController
@RequestMapping(PATH_BILL_TYPE)
public class BillTypeController extends EruController<BillTypeDto, BillTypeFilter> {

    @Autowired
    public BillTypeController(BillTypeService service) {
        super(service);
    }
}
