package com.onurbcd.eruservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ItemDto {

    private UUID id;
    private String name;
}
