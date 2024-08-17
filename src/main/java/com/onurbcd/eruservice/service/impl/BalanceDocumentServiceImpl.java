package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.bogus.MultipartFile;
import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.persistency.repository.BalanceRepository;
import com.onurbcd.eruservice.service.resource.CreateDocument;
import com.onurbcd.eruservice.dto.document.MultipartFileDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.BalanceDocumentService;
import com.onurbcd.eruservice.service.DocumentService;
import com.onurbcd.eruservice.util.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BalanceDocumentServiceImpl implements BalanceDocumentService {

    private final DocumentService documentService;

    private final BalanceRepository balanceRepository;

    @Override
    public CreateDocument createDocuments(BalanceSaveDto saveDto, MultipartFile[] multipartFiles, UUID id) {
        var currentDocuments = getCurrentDocuments(id);
        var newDocuments = saveDocuments(saveDto, multipartFiles);
        var updateDocuments = getDocumentsToUpdate(currentDocuments, saveDto);
        var saveDocuments = Stream.concat(newDocuments.stream(), updateDocuments.stream()).collect(Collectors.toSet());
        var deleteDocuments = getDocumentsToDelete(currentDocuments, saveDto);
        return CreateDocument.builder().saveDocuments(saveDocuments).deleteDocuments(deleteDocuments).build();
    }

    @Override
    public void deleteDocuments(Set<Document> documents) {
        if (CollectionUtil.isEmpty(documents)) {
            return;
        }

        documentService.delete(documents);
    }

    private Set<Document> saveDocuments(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles) {
        if (multipartFiles == null || multipartFiles.length < 1) {
            return Collections.emptySet();
        }

        var path = getPath(saveDto.getDayCalendarDate());
        var multipartFileDto = MultipartFileDto.builder().path(path).multipartFiles(multipartFiles).build();
        return documentService.save(multipartFileDto);
    }

    private String getPath(LocalDate dayCalendarDate) {
        return EruConstants.BALANCE_DOCUMENT_PATH +
                dayCalendarDate.format(DateTimeFormatter.ofPattern(EruConstants.BALANCE_DOCUMENT_PATH_PATTERN));
    }

    private Set<Document> getCurrentDocuments(@Nullable UUID id) {
        return Optional
                .ofNullable(id)
                .map(balanceRepository::getDocuments)
                .filter(CollectionUtil::isNotEmpty)
                .orElse(Collections.emptySet());
    }

    private Set<Document> getDocumentsToUpdate(Set<Document> currentDocuments, BalanceSaveDto saveDto) {
        return currentDocuments
                .stream()
                .filter(documentPredicate(saveDto))
                .collect(Collectors.toSet());
    }

    private Set<Document> getDocumentsToDelete(Set<Document> currentDocuments, BalanceSaveDto saveDto) {
        return currentDocuments
                .stream()
                .filter(documentPredicate(saveDto).negate())
                .collect(Collectors.toSet());
    }

    private static Predicate<Document> documentPredicate(BalanceSaveDto saveDto) {
        return doc -> Optional
                .ofNullable(saveDto.getDocumentsIds())
                .orElse(Collections.emptySet())
                .contains(doc.getId());
    }
}
