package com.onurbcd.eruservice.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class CategoryFilter extends AbstractFilterable {

    private UUID parentId;

    private Short level;

    private Boolean lastBranch;
}
