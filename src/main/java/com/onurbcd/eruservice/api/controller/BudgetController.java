package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.CopyBudgetDto;
import com.onurbcd.eruservice.dto.SumDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.filter.BudgetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/budget")
public class BudgetController extends EruController<BudgetDto, BudgetFilter> implements SequenceApi {

    private final BudgetService budgetService;

    public BudgetController(BudgetService service) {
        super(service);
        this.budgetService = service;
    }

    @Override
    public ResponseEntity<Void> updateSequence(UUID id, Direction direction) {
        budgetService.updateSequence(id, direction);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sum-month")
    @ResponseStatus(HttpStatus.OK)
    public Set<SumDto> getSumByMonth(BudgetFilter filter) {
        return budgetService.getSumByMonth(filter);
    }

    @PostMapping("/copy")
    @ResponseStatus(HttpStatus.CREATED)
    public void copy(@RequestBody CopyBudgetDto copyBudgetDto) {
        budgetService.copy(copyBudgetDto);
    }

    @Override
    public void swapPosition(UUID id, Short targetSequence) {
        budgetService.swapPosition(id, targetSequence);
    }
}
