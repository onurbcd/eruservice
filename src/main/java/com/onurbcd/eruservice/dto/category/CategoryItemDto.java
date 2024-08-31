package com.onurbcd.eruservice.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CategoryItemDto {

    private UUID id;
    private String name;
}
