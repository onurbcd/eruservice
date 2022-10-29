package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.day.CreateMonthDto;
import com.onurbcd.eruservice.dto.day.DayDto;

import java.util.Set;

public interface DayService {

    void createMonth(CreateMonthDto dto);

    Set<DayDto> getYearsAndMonths();
}
