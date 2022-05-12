package com.onurbcd.eruservice.service.filter;

public interface Filterable {

    Boolean isActive();

    void setActive(Boolean active);

    String getSearch();

    void setSearch(String search);
}
