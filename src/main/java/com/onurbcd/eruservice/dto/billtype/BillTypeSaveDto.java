package com.onurbcd.eruservice.dto.billtype;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.util.Extension;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@ExtensionMethod({Extension.class})
public class BillTypeSaveDto extends PrimeSaveDto {

    private String path;
    private UUID categoryId;

    public static BillTypeSaveDto of(String name, Boolean active, String path, String categoryId) {
        return BillTypeSaveDto
                .builder()
                .name(name.normalizeSpace())
                .active(active)
                .path(path.normalizeSpace())
                .categoryId(UUID.fromString(categoryId))
                .build();
    }
}
