package com.onurbcd.eruservice.dto.budget;

import com.onurbcd.eruservice.dto.PrimePatchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BudgetPatchDto extends PrimePatchDto {

    private Boolean paid;

    public static BudgetPatchDto of(Boolean paid) {
        return new BudgetPatchDto(paid);
    }
}
