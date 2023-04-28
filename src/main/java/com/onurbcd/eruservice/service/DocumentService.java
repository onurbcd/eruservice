package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.document.DocumentDto;
import com.onurbcd.eruservice.dto.document.FileDto;
import com.onurbcd.eruservice.dto.document.MultipartFileDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface DocumentService {

    Set<Document> save(MultipartFileDto dto);

    Page<DocumentDto> getAll(Pageable pageable);

    void delete(Set<Document> documents);

    FileDto getFile(UUID id);
}
