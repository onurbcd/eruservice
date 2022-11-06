package com.onurbcd.eruservice.dto.budget;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BudgetPatchDto extends PrimePatchDto {

    private Boolean paid;
}
