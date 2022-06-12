package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.BudgetDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.repository.BudgetRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.BudgetToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BudgetToEntityMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.util.NumberUtil;
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

    @Autowired
    public BudgetServiceImpl(BudgetRepository repository, BudgetToDtoMapper toDtoMapper,
                             BudgetToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper);
        this.repository = repository;
        this.toDtoMapper = toDtoMapper;
        this.toEntityMapper = toEntityMapper;
    }

    @Override
    public void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
        var budgetDto = (BudgetDto) dto;
        var budget = (Budget) entity;
        var sequence = budget != null ? budget.getSequence() : null;

        Action.checkIf(id == null || NumberUtil.equals(budgetDto.getSequence(), sequence))
                .orElseThrow(Error.SEQUENCE_CHANGED, sequence, budgetDto.getSequence());

        // TODO: se for um update não pode alterar o ano e mês
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        // TODO: adicionar lógica para aumentar o número de sequência se for um insert, e não fazer nada se for udpate
        var budgetDto = (BudgetDto) dto;
        var currentBudget = (Budget) entity;
        var budget = toEntityMapper.apply(budgetDto);
        budget.setId(currentBudget != null ? currentBudget.getId() : null);

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

    // TODO: fazer override do update, pois pode atualizar o active, o sequence e o paid
}
