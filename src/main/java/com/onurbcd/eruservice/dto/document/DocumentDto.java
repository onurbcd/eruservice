package com.onurbcd.eruservice.dto.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDto {

    private UUID id;

    private String name;

    private String path;

    private String mimeType;

    private Long size;

    private String hash;
}
