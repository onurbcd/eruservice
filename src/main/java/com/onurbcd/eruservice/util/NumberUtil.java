package com.onurbcd.eruservice.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

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

    public static BigDecimal add(@Nullable BigDecimal bd1, @Nullable BigDecimal bd2) {
        if (bd1 == null && bd2 == null) {
            return BigDecimal.ZERO;
        }

        if (bd1 == null) {
            return bd2;
        }

        if (bd2 == null) {
            return bd1;
        }

        return bd1.add(bd2);
    }
}
