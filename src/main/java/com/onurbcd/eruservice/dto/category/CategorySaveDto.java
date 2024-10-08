package com.onurbcd.eruservice.dto.category;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CategorySaveDto extends PrimeSaveDto {

    @NotNull
    private UUID parentId;

    @NotNull
    @Min(1)
    private Short level;

    @NotNull
    private Boolean lastBranch;

    @Size(max = 250)
    private String description;
}
