package com.onurbcd.eruservice.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileDto {

    private HttpHeaders headers;

    private long contentLength;

    private MediaType contentType;

    private ByteArrayResource resource;
}
