package com.onurbcd.eruservice.service.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BillDocParams {

    private String path;

    private LocalDate referenceDayCalendarDate;

    private MultipartFile multipartFile;

    public static BillDocParams from(String path, LocalDate referenceDayCalendarDate, MultipartFile multipartFile) {
        return new BillDocParams(path, referenceDayCalendarDate, multipartFile);
    }
}
