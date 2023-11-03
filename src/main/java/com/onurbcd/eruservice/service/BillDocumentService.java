package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface BillDocumentService {

    Document createDocument(BillOpenDto billOpenDto, MultipartFile multipartFile);
}
