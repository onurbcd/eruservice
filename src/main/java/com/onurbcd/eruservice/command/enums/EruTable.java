package com.onurbcd.eruservice.command.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Getter
public enum EruTable {

    SECRET(getSecretHeaders()),
    DAY(getDayHeaders()),
    INCOME_SOURCE(getIncomeSourceHeaders()),
    CATEGORY(getCategoryHeaders());

    private final LinkedHashMap<String, Object> headers;

    private static LinkedHashMap<String, Object> getSecretHeaders() {
        var secretHeaders = getDefaultHeaders(9);
        secretHeaders.put("description", "Description");
        secretHeaders.put("link", "Link");
        secretHeaders.put("username", "Username");
        secretHeaders.put("password", "Password");
        return secretHeaders;
    }

    private static LinkedHashMap<String, Object> getDayHeaders() {
        var dayHeaders = LinkedHashMap.<String, Object>newLinkedHashMap(2);
        dayHeaders.put("calendarYear", "Calendar Year");
        dayHeaders.put("calendarMonth", "Calendar Month");
        return dayHeaders;
    }

    private static LinkedHashMap<String, Object> getIncomeSourceHeaders() {
        return getDefaultHeaders(5);
    }

    private static LinkedHashMap<String, Object> getCategoryHeaders() {
        var categoryHeaders = getDefaultHeaders(10);
        categoryHeaders.put("parentId", "Parent Id");
        categoryHeaders.put("parentName", "Parent Name");
        categoryHeaders.put("level", "Level");
        categoryHeaders.put("lastBranch", "Last Branch");
        categoryHeaders.put("description", "Description");
        return categoryHeaders;
    }

    private static LinkedHashMap<String, Object> getDefaultHeaders(int numMappings) {
        var headers = LinkedHashMap.<String, Object>newLinkedHashMap(numMappings);
        headers.put("id", "Id");
        headers.put("name", "Name");
        headers.put("active", "Active");
        headers.put("createdDate", "Created Date");
        headers.put("lastModifiedDate", "Last Modified Date");
        return headers;
    }
}
