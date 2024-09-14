package com.onurbcd.eruservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.shell.component.flow.SelectItem;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumUtil {

    public static <T extends Enum<T>> List<SelectItem> getItems(T[] values) {
        return Arrays
                .stream(values)
                .map(sourceType -> SelectItem.of(sourceType.name(), sourceType.name()))
                .toList();
    }
}
