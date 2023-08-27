package com.onurbcd.eruservice.dto.billtype;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BillTypeSaveDto extends PrimeSaveDto {

    @NotNull
    @Size(max = Constants.SIZE_250)
    private String path;
}
