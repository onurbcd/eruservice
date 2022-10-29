package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.day.CreateMonthDto;

import java.util.Set;

public interface DayService {

    void createMonth(CreateMonthDto dto);

    Set<Short> getMonths(Short calendarYear);
}
