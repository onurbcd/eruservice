package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.persistency.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void saveFile(Document document, MultipartFile multipartFile);

    void deleteFile(Document document);
}
