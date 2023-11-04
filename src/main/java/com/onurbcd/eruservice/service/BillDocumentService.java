package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.resource.BillDocParams;

public interface BillDocumentService {

    Document createDocument(BillDocParams billDocParams);
}
