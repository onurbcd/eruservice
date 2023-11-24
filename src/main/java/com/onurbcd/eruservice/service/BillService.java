package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.bill.BillCloseDto;
import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface BillService extends CrudService {

    @Transactional
    UUID openBill(BillOpenDto billOpenDto, MultipartFile multipartFile);

    @Transactional
    void closeBill(UUID id, BillCloseDto billCloseDto, MultipartFile multipartFile);
}
