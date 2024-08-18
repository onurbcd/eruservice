package com.onurbcd.eruservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Extension {

    public static String normalizeSpace(String in) {
        return StringUtils.normalizeSpace(in);
    }

    public static String defaultToNull(@Nullable String in) {
        return StringUtils.isBlank(in) ? null : StringUtils.normalizeSpace(in);
    }
}
