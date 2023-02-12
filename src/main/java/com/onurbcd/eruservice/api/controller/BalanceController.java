package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.balance.BalancePatchDto;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.dto.filter.BalanceFilter;
import com.onurbcd.eruservice.service.BalanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/balance")
public class BalanceController extends PrimeController<BalanceSaveDto, BalancePatchDto, BalanceFilter>
        implements SequenceApi {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        super(balanceService);
        this.balanceService = balanceService;
    }

    @Override
    public void updateSequence(UUID id, Direction direction) {
        balanceService.updateSequence(id, direction);
    }

    @Override
    public void swapPosition(UUID id, Short targetSequence) {
        balanceService.swapPosition(id, targetSequence);
    }
}
