package com.onurbcd.eruservice.util;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.shell.standard.ShellOption;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Extension {

    public static String normalizeSpace(String in) {
        return StringUtils.normalizeSpace(in);
    }

    public static String defaultToNull(@Nullable String in) {
        return StringUtils.isBlank(in) || Objects.equals(in, ShellOption.NULL) ? null : StringUtils.normalizeSpace(in);
    }
}
