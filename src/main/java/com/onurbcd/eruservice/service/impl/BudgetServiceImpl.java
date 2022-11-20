package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.budget.BudgetDto;
import com.onurbcd.eruservice.dto.budget.BudgetPatchDto;
import com.onurbcd.eruservice.dto.budget.BudgetSaveDto;
import com.onurbcd.eruservice.dto.budget.BudgetSumDto;
import com.onurbcd.eruservice.dto.budget.CopyBudgetDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.budget.SumDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.persistency.entity.Budget;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.param.SequenceParam;
import com.onurbcd.eruservice.persistency.predicate.BudgetPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BudgetRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.SequenceService;
import com.onurbcd.eruservice.service.SourceService;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.dto.filter.BudgetFilter;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.BudgetToEntityMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.onurbcd.eruservice.service.validation.BudgetValidationService;
import com.onurbcd.eruservice.util.CollectionUtil;
import com.onurbcd.eruservice.util.NumberUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class BudgetServiceImpl extends AbstractCrudService<Budget, BudgetDto, BudgetPredicateBuilder, BudgetSaveDto>
        implements BudgetService {

    private final BudgetRepository repository;

    private final BudgetToEntityMapper toEntityMapper;

    private final BudgetValidationService validationService;

    private final SequenceService<BudgetRepository> sequenceService;

    private final SourceService sourceService;

    public BudgetServiceImpl(BudgetRepository repository, BudgetToEntityMapper toEntityMapper,
                             BudgetValidationService validationService,
                             SequenceService<BudgetRepository> sequenceService, SourceService sourceService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, BudgetPredicateBuilder.class);
        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.validationService = validationService;
        this.sequenceService = sequenceService;
        this.sourceService = sourceService;
    }

    @Override
    public void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id) {
        validationService.validate((BudgetSaveDto) dto, (Budget) entity, id);
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        var budget = (Budget) super.fillValues(dto, entity);
        budget.setSequence(getSequence((Budget) entity, budget));
        return budget;
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return BudgetPredicateBuilder.all((BudgetFilter) filter);
    }

    @Override
    public void update(Dtoable dto, UUID id) {
        var patchDto = (BudgetPatchDto) dto;
        var updatedRowsCount = 0;

        if (patchDto.isActive() != null) {
            updatedRowsCount = repository.updateActive(id, patchDto.getActive());
        } else if (patchDto.getPaid() != null) {
            updatedRowsCount = repository.updatePaid(id, patchDto.getPaid());
        }

        Action.checkIf(updatedRowsCount == 1).orElseThrowNotFound(id);
    }

    @Override
    public void updateSequence(UUID id, Direction direction) {
        var budget = findByIdOrElseThrow(id);
        var sequenceParam = new SequenceParam(budget.getRefYear(), budget.getRefMonth(), budget.getSequence());
        sequenceService.swapSequence(sequenceParam, direction);
    }

    @Override
    public void delete(UUID id) {
        var budget = findByIdOrElseThrow(id);
        repository.deleteById(budget.getId());

        sequenceService.updateNextSequences(new SequenceParam(budget.getRefYear(), budget.getRefMonth(),
                budget.getSequence()));
    }

    @Override
    public Set<SumDto> getSumByMonth(BudgetFilter filter) {
        Action.checkIfNotNull(filter.getRefYear()).orElseThrow(Error.BUDGET_REF_YEAR_IS_NULL);
        Action.checkIfNotNull(filter.getRefMonth()).orElseThrow(Error.BUDGET_REF_MONTH_IS_NULL);
        var sumSet = repository.getSumByMonth(filter.getRefYear(), filter.getRefMonth());
        var paidSum = CollectionUtil.getValue(sumSet, BudgetSumDto::getPaid, BudgetSumDto::getAmount);
        var unpaidSum = CollectionUtil.getValue(sumSet, p -> !p.getPaid(), BudgetSumDto::getAmount);
        var totalSum = NumberUtil.add(paidSum, unpaidSum);
        var size = sumSet.stream().mapToLong(BudgetSumDto::getSize).sum();
        var usableBalanceSum = sourceService.getUsableBalanceSum();
        var balance = NumberUtil.subtract(usableBalanceSum, totalSum);

        return Set.of(SumDto.total(totalSum), SumDto.paid(paidSum), SumDto.unpaid(unpaidSum), SumDto.size(size),
                SumDto.balance(balance));
    }

    @Override
    public void copy(CopyBudgetDto copyBudgetDto) {
        var fromBudget = validationService.validateCopy(copyBudgetDto);

        var toBudget = fromBudget
                .stream()
                .map(b -> toEntityMapper.copy(b, copyBudgetDto.getTo().getYear(), copyBudgetDto.getTo().getMonth()))
                .toList();

        repository.saveAll(toBudget);
    }

    @Override
    public void swapPosition(UUID id, Short targetSequence) {
        var budget = findByIdOrElseThrow(id);
        var param = new SequenceParam(budget.getRefYear(), budget.getRefMonth(), budget.getSequence(), targetSequence);
        sequenceService.swapPosition(param);
    }

    @Override
    public void deleteAll(Short refYear, Short refMonth) {
        var count = repository.deleteAll(refYear, refMonth);
        Action.checkIf(count > 0).orElseThrow(Error.NO_ROWS_DELETED);
    }

    private Short getSequence(Budget current, Budget next) {
        return current != null ? current.getSequence() : sequenceService
                .getNextSequence(new SequenceParam(next.getRefYear(), next.getRefMonth()));
    }
}
