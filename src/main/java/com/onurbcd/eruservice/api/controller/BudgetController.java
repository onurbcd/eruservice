package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.filter.BudgetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/budget")
public class BudgetController extends EruController<BudgetDto, BudgetFilter> implements SequenceApi {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService service) {
        super(service);
        this.budgetService = service;
    }

    @Override
    public ResponseEntity<Void> updateSequence(UUID id, Direction direction) {
        budgetService.updateSequence(id, direction);
        return ResponseEntity.noContent().build();
    }
}
