package com.onurbcd.eruservice.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.lang.Nullable;

public class NumberUtil {

    private NumberUtil() {
    }

    public static boolean equals(@Nullable Short s1, @Nullable Short s2) {
        if (s1 == null && s2 == null) {
            return true;
        }

        if (s1 == null || s2 == null) {
            return false;
        }

        return NumberUtils.compare(s1, s2) == 0;
    }
}
