package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.dto.document.MultipartFileDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.BillDocumentService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BillDocumentServiceImpl implements BillDocumentService {

    private final DocumentService documentService;

    private final BillTypeService billTypeService;

    @Override
    public Document createDocument(BillOpenDto billOpenDto, MultipartFile multipartFile) {
        var path = getPath(billOpenDto);

        var multipartFileDto = MultipartFileDto
                .builder()
                .path(path)
                .multipartFiles(new MultipartFile[]{multipartFile})
                .build();

        var documents = documentService.save(multipartFileDto);
        return documents.iterator().next();
    }

    private String getPath(BillOpenDto billOpenDto) {
        var billTypePath = billTypeService.getPathById(billOpenDto.getBillTypeId());

        var referenceDayPath = billOpenDto
                .getReferenceDayCalendarDate()
                .format(DateTimeFormatter.ofPattern(EruConstants.BILL_DOCUMENT_PATH_PATTERN));

        return EruConstants.BILL_DOCUMENT_PATH + billTypePath + "/" + referenceDayPath;
    }
}
