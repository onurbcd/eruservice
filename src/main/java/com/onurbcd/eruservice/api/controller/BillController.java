package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.bill.BillCloseDto;
import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.dto.bill.BillPatchDto;
import com.onurbcd.eruservice.dto.bill.BillSaveDto;
import com.onurbcd.eruservice.dto.filter.BillFilter;
import com.onurbcd.eruservice.service.BillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/bill")
public class BillController extends PrimeController<BillSaveDto, BillPatchDto, BillFilter> {

    private final BillService billService;

    public BillController(BillService billService) {
        super(billService);
        this.billService = billService;
    }

    @PostMapping(path = "/openBill", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void openBill(@Valid @RequestPart("billOpen") BillOpenDto billOpenDto,
                         @RequestPart(value = "billDocument") MultipartFile multipartFile) {

        billService.openBill(billOpenDto, multipartFile);
    }

    @PutMapping(path = "/closeBill/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void closeBill(@PathVariable("id") UUID id, @Valid @RequestPart("billClose") BillCloseDto billCloseDto,
                          @RequestPart(value = "receipt") MultipartFile multipartFile) {

        billService.closeBill(id, billCloseDto, multipartFile);
    }
}
