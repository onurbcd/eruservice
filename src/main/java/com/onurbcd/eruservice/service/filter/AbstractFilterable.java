package com.onurbcd.eruservice.service.filter;

public abstract class AbstractFilterable implements Filterable {

    private Boolean active;

    @Override
    public Boolean isActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }
}
