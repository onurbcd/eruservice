package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.document.DocumentDto;
import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.persistency.repository.DocumentRepository;
import com.onurbcd.eruservice.service.DocumentService;
import com.onurbcd.eruservice.service.StorageService;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import com.onurbcd.eruservice.service.mapper.DocumentToDtoMapper;
import com.onurbcd.eruservice.service.validation.DocumentValidationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final int RADIX = 16;

    private final DocumentRepository repository;

    private final StorageService storageService;

    private final DocumentToDtoMapper toDtoMapper;

    private final DocumentValidationService validationService;

    @Override
    public void saveAll(@Nullable MultipartFile[] multipartFiles) {
        if (multipartFiles == null || multipartFiles.length < 1) {
            return;
        }

        for (var multipartFile : multipartFiles) {
            validationService.validate(multipartFile);
            create(multipartFile);
        }
    }

    @Override
    public Page<DocumentDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(toDtoMapper);
    }

    private void create(MultipartFile multipartFile) {
        var document = new Document();
        document.setName(multipartFile.getOriginalFilename());
        document.setPath(StringUtils.EMPTY);
        document.setMimeType(multipartFile.getContentType());
        document.setSize(multipartFile.getSize());
        var hash = generateHash(document);
        document.setHash(hash);
        storageService.saveFile(document, multipartFile);
        repository.save(document);
    }

    private String generateHash(Document document) {
        try {
            var transformedName = document.getName() + document.getPath() + document.getMimeType() +
                    document.getSize() + new Date().getTime();

            var messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, messageDigest.digest()).toString(RADIX);
        } catch (NoSuchAlgorithmException e) {
            throw new ApiException(Error.DOCUMENT_GENERATE_HASH, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
