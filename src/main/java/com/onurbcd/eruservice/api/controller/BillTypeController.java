package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.billtype.BillTypePatchDto;
import com.onurbcd.eruservice.dto.billtype.BillTypeSaveDto;
import com.onurbcd.eruservice.dto.filter.BillTypeFilter;
import com.onurbcd.eruservice.service.BillTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill-type")
public class BillTypeController extends PrimeController<BillTypeSaveDto, BillTypePatchDto, BillTypeFilter> {

    public BillTypeController(BillTypeService billTypeService) {
        super(billTypeService);
    }
}
