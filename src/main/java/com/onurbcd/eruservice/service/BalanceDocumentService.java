package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.document.CreateDocumentDto;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public interface BalanceDocumentService {

    CreateDocumentDto createDocuments(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles);
}
