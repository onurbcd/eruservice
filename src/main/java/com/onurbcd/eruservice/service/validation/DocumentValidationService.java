package com.onurbcd.eruservice.service.validation;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentValidationService {

    void validate(MultipartFile multipartFile);
}
