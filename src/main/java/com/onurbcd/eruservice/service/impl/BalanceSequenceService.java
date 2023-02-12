package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.persistency.repository.BalanceRepository;
import com.onurbcd.eruservice.service.AbstractSequenceService;
import com.onurbcd.eruservice.service.validation.SequenceValidationService;

@PrimeService(Domain.BALANCE_SEQUENCE)
public class BalanceSequenceService extends AbstractSequenceService {

    public BalanceSequenceService(
            BalanceRepository repository,
            @PrimeService(Domain.BALANCE_SEQUENCE_VALIDATION) SequenceValidationService validationService) {

        super(repository, validationService);
    }
}
