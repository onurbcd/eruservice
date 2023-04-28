package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.property.AdminProperties;
import com.onurbcd.eruservice.service.StorageService;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import com.onurbcd.eruservice.service.validation.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final AdminProperties config;

    @Override
    public void saveFile(Document document, MultipartFile multipartFile) {
        try (var inputStream = multipartFile.getInputStream()) {
            var storagePath = Paths.get(config.getStoragePath(), document.getPath()).toAbsolutePath().normalize();
            var filePath = getFilePath(storagePath, document);
            Action.checkIf(Files.notExists(filePath)).orElseThrow(Error.FILE_ALREADY_EXISTS, filePath.toString());
            Files.createDirectories(storagePath);
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            log.error("Storage File Save", e);
            throw new ApiException(Error.STORAGE_FILE_SAVE, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteFile(Document document) {
        try {
            var filePath = getFilePath(document);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Storage File Delete", e);
            throw new ApiException(Error.STORAGE_FILE_DELETE, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public byte[] getFile(Document document) {
        try {
            var filePath = getFilePath(document);
            var fileExists = Files.exists(filePath);
            Action.checkIf(fileExists).orElseThrow(Error.FILE_DOES_NOT_EXIST, filePath.toString());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.error("Storage File Get", e);
            throw new ApiException(Error.STORAGE_FILE_GET, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Path getFilePath(Document document) {
        var storagePath = Paths.get(config.getStoragePath(), document.getPath()).toAbsolutePath().normalize();
        return getFilePath(storagePath, document);
    }

    private Path getFilePath(Path storagePath, Document document) {
        var extension = StringUtils.getFilenameExtension(document.getName());
        var fileName = document.getHash() + "." + extension;
        return storagePath.resolve(fileName);
    }
}
