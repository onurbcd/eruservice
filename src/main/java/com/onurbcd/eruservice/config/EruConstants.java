package com.onurbcd.eruservice.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EruConstants {

    public static final String BALANCE_DOCUMENT_PATH = "balance/";

    public static final String BALANCE_DOCUMENT_PATH_PATTERN = "yyyy/MM/dd";

    public static final String BOGUS_NAME = "bogus";

    public static final String BILL_DOCUMENT_PATH = "bill/";

    public static final String YEAR_PATTERN = "yyyy";

    public static final String BILL_CLOSE_CODE = "created by bill close";

    public static final String MONTH_YEAR_PATTERN = "MM/yyyy";
}
