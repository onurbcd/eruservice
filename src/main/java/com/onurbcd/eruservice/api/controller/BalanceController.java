package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.balance.BalancePatchDto;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.balance.BalanceSumDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.dto.filter.BalanceFilter;
import com.onurbcd.eruservice.service.BalanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
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

    @PostMapping(path = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestPart("balance") BalanceSaveDto saveDto,
                     @RequestPart(value = "documents", required = false) MultipartFile[] multipartFiles) {

        balanceService.save(saveDto, multipartFiles, null);
    }

    @PutMapping(path = "/save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @Valid @RequestPart("balance") BalanceSaveDto saveDto,
                       @RequestPart(value = "documents", required = false) MultipartFile[] multipartFiles) {

        balanceService.save(saveDto, multipartFiles, id);
    }

    @GetMapping("/sum")
    @ResponseStatus(HttpStatus.OK)
    public Set<BalanceSumDto> getSum(BalanceFilter filter) {
        return balanceService.getSum(filter);
    }
}
