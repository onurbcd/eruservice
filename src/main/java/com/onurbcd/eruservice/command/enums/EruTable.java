package com.onurbcd.eruservice.command.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Getter
public enum EruTable {

    SECRET(getSecretHeaders());

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
}
