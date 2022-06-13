package com.onurbcd.eruservice.persistency.param;

public abstract class AbstractSequenceParam implements Sequenceable {

    private Short year;

    private Short month;

    protected AbstractSequenceParam(Short year, Short month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public Short getYear() {
        return year;
    }

    @Override
    public void setYear(Short year) {
        this.year = year;
    }

    @Override
    public Short getMonth() {
        return month;
    }

    @Override
    public void setMonth(Short month) {
        this.month = month;
    }
}
