package com.onurbcd.eruservice.command.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onurbcd.eruservice.command.enums.EruTable;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.util.BigDecimalFormatter;
import com.onurbcd.eruservice.util.LocalDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.CellMatchers;
import org.springframework.shell.table.SimpleHorizontalAligner;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ShellHelper {

    private final ObjectMapper eruMapper;
    private final LocalDateTimeFormatter localDateTimeFormatter;
    private final BigDecimalFormatter bigDecimalFormatter;

    public String printJson(Dtoable dtoable) throws JsonProcessingException {
        return eruMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoable);
    }

    public String printTable(Page<Dtoable> page, EruTable table) {
        var tableBuilder = new TableBuilder(new BeanListTableModel<>(page.getContent(), table.getHeaders()))
                .addFullBorder(BorderStyle.fancy_heavy)
                .on(CellMatchers.ofType(LocalDateTime.class)).addFormatter(localDateTimeFormatter)
                .on(CellMatchers.ofType(BigDecimal.class)).addFormatter(bigDecimalFormatter)
                .on(CellMatchers.ofType(Number.class)).addAligner(SimpleHorizontalAligner.right);

        var pageInfo = String.format("%nNumber Of Elements: %d%nCurrent Page: %d%nTotal Elements: %d%nTotal Pages: %d",
                page.getNumberOfElements(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages());

        return tableBuilder.build().render(1000) + pageInfo;
    }

    public <T> String printTable(Iterable<T> iterable, EruTable table) {
        return new TableBuilder(new BeanListTableModel<>(iterable, table.getHeaders()))
                .addFullBorder(BorderStyle.fancy_heavy)
                .on(CellMatchers.ofType(BigDecimal.class)).addFormatter(bigDecimalFormatter)
                .on(CellMatchers.ofType(Number.class)).addAligner(SimpleHorizontalAligner.right)
                .build()
                .render(1000);
    }
}
