package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.document.MultipartFileDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.BillDocumentService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.DocumentService;
import com.onurbcd.eruservice.service.resource.BillDocParams;
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
    public Document createDocument(BillDocParams billDocParams) {
        var path = getPath(billDocParams);

        var multipartFileDto = MultipartFileDto
                .builder()
                .path(path)
                .multipartFiles(new MultipartFile[]{billDocParams.getMultipartFile()})
                .build();

        var documents = documentService.save(multipartFileDto);
        return documents.iterator().next();
    }

    private String getPath(BillDocParams billDocParams) {
        var billTypePath = billTypeService.getPathById(billDocParams.getBillTypeId());

        var referenceDayPath = billDocParams
                .getReferenceDayCalendarDate()
                .format(DateTimeFormatter.ofPattern(EruConstants.BILL_DOCUMENT_PATH_PATTERN));

        return EruConstants.BILL_DOCUMENT_PATH + billTypePath + "/" + referenceDayPath;
    }
}
