package com.onurbcd.eruservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    public static boolean equalMonth(@Nullable Short refYear, @Nullable Short refMonth, @Nullable Short oRefYear,
                                     @Nullable Short oRefMonth) {

        if (refYear == null || refMonth == null || oRefYear == null || oRefMonth == null) {
            return false;
        }

        return refYear.equals(oRefYear) && refMonth.equals(oRefMonth);
    }
}
