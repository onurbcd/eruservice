package com.onurbcd.eruservice.service.validation.impl;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.persistency.entity.Balance;
import com.onurbcd.eruservice.persistency.repository.BalanceRepository;
import com.onurbcd.eruservice.service.validation.BalanceValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceValidationServiceImpl implements BalanceValidationService {

    private final BalanceRepository repository;

    @Override
    public void validate(BalanceSaveDto dto, Balance balance, UUID id) {
        /*
            sequence não pode ser atualizado
            day não pode ser atualizado (transformar o calendar date num day id)
            source não pode ser atualizado
            balanceType não pode ser atualizado
         */
    }
}
