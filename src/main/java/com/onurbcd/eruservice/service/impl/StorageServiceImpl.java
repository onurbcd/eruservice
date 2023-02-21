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
            var extension = StringUtils.getFilenameExtension(document.getName());
            var fileName = document.getHash() + "." + extension;
            var filePath = storagePath.resolve(fileName);
            Action.checkIf(Files.notExists(filePath)).orElseThrow(Error.FILE_ALREADY_EXISTS, filePath.toString());
            Files.createDirectories(storagePath);
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            log.error("Storage File Save", e);
            throw new ApiException(Error.STORAGE_FILE_SAVE, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
