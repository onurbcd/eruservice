package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.day.CreateMonthDto;
import com.onurbcd.eruservice.persistency.constraint.MaxYear;
import com.onurbcd.eruservice.persistency.constraint.MinYear;
import com.onurbcd.eruservice.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/day")
@RequiredArgsConstructor
@Validated
public class DayController {

    private final DayService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMonth(@Valid @RequestBody CreateMonthDto dto) {
        service.createMonth(dto);
    }

    @GetMapping("/{calendarYear}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Short> getMonths(@PathVariable("calendarYear") @Valid @MinYear @MaxYear Short calendarYear) {
        return service.getMonths(calendarYear);
    }
}
