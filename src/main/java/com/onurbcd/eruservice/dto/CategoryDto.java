package com.onurbcd.eruservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto extends PrimeDto {

    private UUID parentId;

    private String parentName;

    private Short level;

    private Boolean lastBranch;

    private String description;
}
