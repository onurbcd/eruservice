package com.onurbcd.eruservice.dto.budget;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CopyBudgetDto {

    private RefDto from;

    private RefDto to;
}
