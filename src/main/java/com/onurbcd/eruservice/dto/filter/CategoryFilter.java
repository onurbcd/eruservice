package com.onurbcd.eruservice.dto.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CategoryFilter extends AbstractFilterable {

    private UUID parentId;

    private Short level;

    private Boolean lastBranch;
}
