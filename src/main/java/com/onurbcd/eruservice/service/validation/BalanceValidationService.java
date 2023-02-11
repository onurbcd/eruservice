package com.onurbcd.eruservice.service.validation;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.persistency.entity.Balance;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface BalanceValidationService {

    void validate(BalanceSaveDto dto, @Nullable Balance balance, @Nullable UUID id);
}
