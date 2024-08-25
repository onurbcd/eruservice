package com.onurbcd.eruservice.dto.billtype;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class BillTypeSaveDto extends PrimeSaveDto {

    @NotNull
    @Size(min = Constants.SIZE_3, max = Constants.SIZE_250)
    @Pattern(regexp = Constants.REGEXP_PATH)
    private String path;

    @NotNull
    private UUID categoryId;
}
