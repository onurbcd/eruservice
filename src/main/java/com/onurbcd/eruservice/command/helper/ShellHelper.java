package com.onurbcd.eruservice.command.helper;

import com.onurbcd.eruservice.command.enums.EruTable;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.util.LocalDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.CellMatchers;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ShellHelper {

    private final LocalDateTimeFormatter localDateTimeFormatter;

    public String printTable(Page<Dtoable> page, EruTable table) {
        var tableModel = new BeanListTableModel<>(page.getContent(), table.getHeaders());

        var tableBuilder = new TableBuilder(tableModel);
        tableBuilder.addFullBorder(BorderStyle.fancy_heavy);
        tableBuilder.on(CellMatchers.ofType(LocalDateTime.class)).addFormatter(localDateTimeFormatter);

        var pageInfo = String.format("%nNumber Of Elements: %d%nCurrent Page: %d%nTotal Elements: %d%nTotal Pages: %d",
                page.getNumberOfElements(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages());

        return tableBuilder.build().render(1000) + pageInfo;
    }
}
