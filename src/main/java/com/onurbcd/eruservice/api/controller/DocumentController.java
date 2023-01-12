package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.document.DocumentDto;
import com.onurbcd.eruservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@RequestParam("documents") MultipartFile[] multipartFiles) {
        service.saveAll(multipartFiles);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DocumentDto> getAll(@PageableDefault(sort = "name") Pageable pageable) {
        return service.getAll(pageable);
    }
}
