package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.bogus.MultipartFile;

public interface DocumentValidationService {

    void validate(MultipartFile multipartFile);
}
