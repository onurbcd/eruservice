package com.onurbcd.eruservice.dto.category;

import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.util.Extension;
import lombok.Getter;
import lombok.Setter;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@ExtensionMethod({Extension.class})
public class CategorySaveDto extends PrimeSaveDto {

    private UUID parentId;
    private String description;

    public static CategorySaveDto of(String name, String parentId, @Nullable String description) {
        return CategorySaveDto
                .builder()
                .name(name.normalizeSpace())
                .active(Boolean.TRUE)
                .parentId(UUID.fromString(parentId))
                .description(description.defaultToNull())
                .build();
    }
}
