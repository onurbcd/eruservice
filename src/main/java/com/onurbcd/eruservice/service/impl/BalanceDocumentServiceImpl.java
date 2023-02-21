package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.document.CreateDocumentDto;
import com.onurbcd.eruservice.dto.document.MultipartFileDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.BalanceDocumentService;
import com.onurbcd.eruservice.service.DocumentService;
import com.onurbcd.eruservice.util.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceDocumentServiceImpl implements BalanceDocumentService {

    private final DocumentService documentService;

    @Override
    public CreateDocumentDto createDocuments(BalanceSaveDto saveDto, MultipartFile[] multipartFiles) {
        var newDocuments = saveDocuments(saveDto, multipartFiles);
        var deleteDocuments = getDocumentsToDelete(saveDto);
        return CreateDocumentDto.builder().newDocuments(newDocuments).deleteDocuments(deleteDocuments).build();
    }

    @Nullable
    private Set<Document> saveDocuments(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles) {
        if (multipartFiles == null || multipartFiles.length < 1) {
            return null;
        }

        var path = getPath(saveDto.getDayCalendarDate());
        var multipartFileDto = MultipartFileDto.builder().path(path).multipartFiles(multipartFiles).build();
        return documentService.save(multipartFileDto);
    }

    private String getPath(LocalDate dayCalendarDate) {
        return EruConstants.BALANCE_DOCUMENT_PATH +
                dayCalendarDate.format(DateTimeFormatter.ofPattern(EruConstants.BALANCE_DOCUMENT_PATH_PATTERN));
    }

    private Set<Document> getDocumentsToDelete(BalanceSaveDto saveDto) {
        if (CollectionUtil.isEmpty(saveDto.getDocumentsIds())) {
            return Collections.emptySet();
        }

        var currentDocuments = documentService.getAllById(saveDto.getDocumentsIds());

        return currentDocuments
                .stream()
                .filter(doc -> !saveDto.getDocumentsIds().contains(doc.getId()))
                .collect(Collectors.toSet());
    }
}
