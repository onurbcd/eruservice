package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.persistency.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class BillDocParams {

    private UUID billTypeId;

    private LocalDate referenceDayCalendarDate;

    private MultipartFile multipartFile;

    public static BillDocParams from(BillOpenDto billOpenDto, MultipartFile multipartFile) {
        return new BillDocParams(billOpenDto.getBillTypeId(), billOpenDto.getReferenceDayCalendarDate(), multipartFile);
    }

    public static BillDocParams from(Bill bill, MultipartFile multipartFile) {
        return new BillDocParams(bill.getBillType().getId(), bill.getReferenceDay().getCalendarDate(), multipartFile);
    }
}
