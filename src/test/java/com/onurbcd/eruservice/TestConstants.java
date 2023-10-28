package com.onurbcd.eruservice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConstants {

    public static final String NAME = "name";
    public static final Short SEQUENCE = 1;
    public static final Short YEAR = 2023;
    public static final Short MONTH = 9;
    public static final String PATH = "path";
    public static final Short TARGET_SEQUENCE = 2;
    public static final UUID ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
}
