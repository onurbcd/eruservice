package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.day.CreateMonthDto;
import com.onurbcd.eruservice.persistency.repository.DayRepository;
import com.onurbcd.eruservice.service.validation.DayValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
class DayServiceImplTest {

    @InjectMocks
    private DayServiceImpl service;

    @Mock
    private DayValidationService validationService;

    @Mock
    private DayRepository repository;

    @Test
    void createMonthTest() {
        var dto = CreateMonthDto.of((short) 2022, (short) 1);
        Mockito.doNothing().when(validationService).validate(dto);
        Mockito.when(repository.saveAll(Mockito.anyCollection())).thenReturn(Collections.emptyList());
        service.createMonth(dto);
    }
}
