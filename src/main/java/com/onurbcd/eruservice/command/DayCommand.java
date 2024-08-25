package com.onurbcd.eruservice.command;

import com.onurbcd.eruservice.command.enums.EruTable;
import com.onurbcd.eruservice.command.helper.ShellHelper;
import com.onurbcd.eruservice.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Day")
@RequiredArgsConstructor
public class DayCommand {

    private final DayService service;
    private final ShellHelper shellHelper;

    @ShellMethod(key = "day-get", value = "Get list of months.")
    public String get() {
        return shellHelper.printTable(service.getYearsAndMonths(), EruTable.DAY);
    }
}
