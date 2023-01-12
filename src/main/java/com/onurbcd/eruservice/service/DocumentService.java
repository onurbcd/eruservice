package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.document.DocumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    void saveAll(MultipartFile[] multipartFiles);

    Page<DocumentDto> getAll(Pageable pageable);
}
