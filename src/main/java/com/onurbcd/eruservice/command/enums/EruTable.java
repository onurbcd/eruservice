package com.onurbcd.eruservice.command.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Getter
public enum EruTable {

    SECRET(getSecretHeaders()),
    DAY(getDayHeaders()),
    INCOME_SOURCE(getIncomeSourceHeaders());

    private final LinkedHashMap<String, Object> headers;

    private static LinkedHashMap<String, Object> getSecretHeaders() {
        var secretHeaders = LinkedHashMap.<String, Object>newLinkedHashMap(9);
        secretHeaders.put("id", "Id");
        secretHeaders.put("name", "Name");
        secretHeaders.put("active", "Active");
        secretHeaders.put("description", "Description");
        secretHeaders.put("link", "Link");
        secretHeaders.put("username", "Username");
        secretHeaders.put("password", "Password");
        secretHeaders.put("createdDate", "Created Date");
        secretHeaders.put("lastModifiedDate", "Last Modified Date");
        return secretHeaders;
    }

    private static LinkedHashMap<String, Object> getDayHeaders() {
        var dayHeaders = LinkedHashMap.<String, Object>newLinkedHashMap(9);
        dayHeaders.put("calendarYear", "Calendar Year");
        dayHeaders.put("calendarMonth", "Calendar Month");
        return dayHeaders;
    }

    private static LinkedHashMap<String, Object> getIncomeSourceHeaders() {
        var incomeSourceHeaders = LinkedHashMap.<String, Object>newLinkedHashMap(9);
        incomeSourceHeaders.put("id", "Id");
        incomeSourceHeaders.put("name", "Name");
        incomeSourceHeaders.put("active", "Active");
        incomeSourceHeaders.put("createdDate", "Created Date");
        incomeSourceHeaders.put("lastModifiedDate", "Last Modified Date");
        return incomeSourceHeaders;
    }
}
