package com.onurbcd.eruservice.dto.filter;

public interface Filterable {

    Boolean isActive();

    void setActive(Boolean active);

    String getSearch();

    void setSearch(String search);
}
