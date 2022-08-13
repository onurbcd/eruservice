package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.filter.BillTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill-type")
public class BillTypeController extends EruController<BillTypeDto, BillTypeFilter> {

    public BillTypeController(BillTypeService service) {
        super(service);
    }
}
