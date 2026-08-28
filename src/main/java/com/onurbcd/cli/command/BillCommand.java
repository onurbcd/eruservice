package com.onurbcd.cli.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onurbcd.cli.annotation.MaxYear;
import com.onurbcd.cli.annotation.MinYear;
import com.onurbcd.cli.config.property.AdminProperties;
import com.onurbcd.cli.dto.bill.BillDto;
import com.onurbcd.cli.enums.Error;
import com.onurbcd.cli.enums.EruTable;
import com.onurbcd.cli.enums.FlowType;
import com.onurbcd.cli.helper.ShellHelper;
import com.onurbcd.cli.param.CommandParam;
import com.onurbcd.cli.param.flow.SaveFlowParam;
import com.onurbcd.cli.service.BillService;
import com.onurbcd.cli.service.BudgetService;
import com.onurbcd.cli.service.SourceService;
import com.onurbcd.cli.util.DateUtil;
import com.onurbcd.cli.validator.Action;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.UUID;

import static com.onurbcd.cli.util.Constant.BILL;

@ShellComponent
@ShellCommandGroup(BILL)
public class BillCommand extends BaseCommand {

    private final BillService service;
    private final BudgetService budgetService;
    private final SourceService sourceService;
    private final AdminProperties config;

    public BillCommand(BillService service, ComponentFlow.Builder flowBuilder, ShellHelper shellHelper,
                       BudgetService budgetService, SourceService sourceService, AdminProperties config) {

        super(service, flowBuilder, shellHelper, BILL, EruTable.BILL);
        this.service = service;
        this.budgetService = budgetService;
        this.sourceService = sourceService;
        this.config = config;
    }

    @ShellMethod(key = "bill-save", value = "Update a bill.")
    public String save(
            @ShellOption(value = {"id", "-i"}, help = "The bill's id.", defaultValue = ShellOption.NULL)
            @NotNull
            UUID id
    ) {
        return baseSave(CommandParam.of(FlowType.BILL, id));
    }

    @ShellMethod(key = "bill-get", value = "Get bill by id.")
    public String get(
            @ShellOption(value = {"id", "-i"}, help = "The bill's id.")
            @NotNull
            UUID id
    ) throws JsonProcessingException {
        return baseGet(id);
    }

    @ShellMethod(key = "bill-open", value = "Open a bill.")
    public String openBill(
            @ShellOption(value = {"year", "-y"}, help = "The reference year.", defaultValue = ShellOption.NULL)
            @MinYear
            @MaxYear
            Short year,

            @ShellOption(value = {"month", "-m"}, help = "The reference month.", defaultValue = ShellOption.NULL)
            @Min(1)
            @Max(12)
            Short month
    ) {
        return baseSave(CommandParam.of(FlowType.BILL_OPEN, year, month));
    }

    @ShellMethod(key = "bill-close", value = "Close a bill.")
    public String closeBill(
            @ShellOption(value = {"year", "-y"}, help = "The reference year.", defaultValue = ShellOption.NULL)
            @MinYear
            @MaxYear
            Short year,

            @ShellOption(value = {"month", "-m"}, help = "The reference month.", defaultValue = ShellOption.NULL)
            @Min(1)
            @Max(12)
            Short month
    ) {
        return baseSave(CommandParam.of(FlowType.BILL_CLOSE, year, month));
    }

    @Override
    protected SaveFlowParam preSaveFlow(CommandParam params) {
        switch (params.getFlowType()) {
            case BILL -> {
                var dto = (BillDto) params.getDto();
                Short year = (short) dto.getReferenceDayCalendarDate().getYear();
                Short month = (short) dto.getReferenceDayCalendarDate().getMonthValue();
                var budgetItems = budgetService.getMonthlyBudget(year, month);
                Action.checkIfNotEmpty(budgetItems).orElseThrow(Error.BUDGET_REQUIRED, month, year);
                var sourceItems = sourceService.getItems(null);
                Action.checkIfNotEmpty(sourceItems).orElseThrow(Error.SOURCE_REQUIRED);
                return SaveFlowParam.bill(budgetItems, sourceItems, config.getFilesPath());
            }
            case BILL_OPEN -> {
                var year = DateUtil.orCurrentYear(params.getYear());
                var month = DateUtil.orCurrentMonth(params.getMonth());
                var budgetItems = budgetService.getMonthlyBudget(year, month);
                Action.checkIfNotEmpty(budgetItems).orElseThrow(Error.BUDGET_REQUIRED, month, year);
                return SaveFlowParam.billOpen(budgetItems, config.getFilesPath());
            }
            case BILL_CLOSE -> {
                var year = DateUtil.orCurrentYear(params.getYear());
                var month = DateUtil.orCurrentMonth(params.getMonth());
                var billItems = service.getOpenBills(year, month);
                Action.checkIfNotEmpty(billItems).orElseThrow(Error.OPEN_BILLS_REQUIRED, month, year);
                var sourceItems = sourceService.getItems(null);
                Action.checkIfNotEmpty(sourceItems).orElseThrow(Error.SOURCE_REQUIRED);
                return SaveFlowParam.billClose(billItems, sourceItems, config.getFilesPath());
            }
            default -> throw new IllegalStateException("Unexpected value: " + params.getFlowType());
        }
    }
}
