package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.dto.enums.DocumentType;
import com.onurbcd.eruservice.dto.enums.ReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillDocParams {

    private String path;

    private LocalDate referenceDayCalendarDate;

    private MultipartFile multipartFile;

    private DocumentType documentType;

    private ReferenceType referenceType;
}
