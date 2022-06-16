package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.param.BudgetSequenceParam;
import com.onurbcd.eruservice.persistency.repository.BudgetRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.SequenceService;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.BudgetToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BudgetToEntityMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.BudgetValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BudgetServiceImpl extends AbstractCrudService<Budget, BudgetDto> implements BudgetService {

    private final BudgetRepository repository;

    private final BudgetToDtoMapper toDtoMapper;

    private final BudgetToEntityMapper toEntityMapper;

    private final BudgetValidationService validationService;

    private final SequenceService<BudgetRepository> sequenceService;

    @Autowired
    public BudgetServiceImpl(BudgetRepository repository, BudgetToDtoMapper toDtoMapper,
                             BudgetToEntityMapper toEntityMapper, BudgetValidationService validationService,
                             SequenceService<BudgetRepository> sequenceService) {

        super(repository, toDtoMapper);
        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
        this.toEntityMapper = toEntityMapper;
        this.validationService = validationService;
        this.sequenceService = sequenceService;
    }

    @Override
    public void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id) {
        var budgetDto = (BudgetDto) dto;
        var budget = (Budget) entity;
        validationService.validate(budgetDto, budget, id);
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        var budgetDto = (BudgetDto) dto;
        var currentBudget = (Budget) entity;
        var budget = toEntityMapper.apply(budgetDto);
        budget.setId(currentBudget != null ? currentBudget.getId() : null);
        budget.setSequence(getSequence(currentBudget, budget));

        if (currentBudget != null) {
            budget.setCreatedDate(currentBudget.getCreatedDate());
        }

        return budget;
    }

    @Override
    public Page<Dtoable> getAll(Pageable pageable, Filterable filter) {
        // TODO: implementar o getAll
        return null;
    }

    @Override
    public void update(Dtoable dto, UUID id) {
        var entity = repository.findById(id).orElse(null);
        Action.checkIfNotNull(entity).orElseThrowNotFound(id);
        assert entity != null;
        var budgetDto = (BudgetDto) dto;
        var changed = false;

        if (budgetDto.isActive() != null) {
            entity.setActive(budgetDto.isActive());
            changed = true;
        }

        if (budgetDto.getPaid() != null) {
            entity.setPaid(budgetDto.getPaid());
            changed = true;
        }

        if (changed) {
            repository.save(entity);
        }
    }

    @Override
    public void changeSequence(UUID id, Direction direction) {
        sequenceService.changeSequence(id, direction);
    }

    // TODO: fazer override ao delete, pois precisa de alterar a sequencia dos budgets da mesma referência ano/mês

    private Short getSequence(Budget current, Budget next) {
        return current != null ? current.getSequence() : sequenceService.getNextSequence(new BudgetSequenceParam(next));
    }
}
