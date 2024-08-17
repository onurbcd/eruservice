package com.onurbcd.eruservice.bogus;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
public class MultipartFile {

    private String originalFilename;

    private String contentType;

    private Long size;

    private InputStream inputStream;

    public boolean isEmpty() {
        return false;
    }
}
