package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface BillService extends CrudService {

    @Transactional
    void openBill(BillOpenDto billOpenDto, MultipartFile multipartFile);

    // @Transactional
    // void closeBill();
}
