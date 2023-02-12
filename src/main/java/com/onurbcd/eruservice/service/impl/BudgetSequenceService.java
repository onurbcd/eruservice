package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.persistency.repository.BudgetRepository;
import com.onurbcd.eruservice.service.AbstractSequenceService;
import com.onurbcd.eruservice.service.validation.SequenceValidationService;

@PrimeService(Domain.BUDGET_SEQUENCE)
public class BudgetSequenceService extends AbstractSequenceService {

    public BudgetSequenceService(
            BudgetRepository repository,
            @PrimeService(Domain.BUDGET_SEQUENCE_VALIDATION) SequenceValidationService validationService) {

        super(repository, validationService);
    }
}
