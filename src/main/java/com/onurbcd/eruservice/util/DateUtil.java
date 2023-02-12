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

    public static boolean equalDay(@Nullable Short year, @Nullable Short month, @Nullable Short day,
                                   @Nullable Short oYear, @Nullable Short oMonth, @Nullable Short oDay) {

        if (year == null || month == null || day == null || oYear == null || oMonth == null || oDay == null) {
            return false;
        }

        return year.equals(oYear) && month.equals(oMonth) && day.equals(oDay);
    }
}
