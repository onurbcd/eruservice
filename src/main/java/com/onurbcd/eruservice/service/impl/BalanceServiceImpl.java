package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.balance.BalanceDto;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.dto.filter.BalanceFilter;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.persistency.entity.Balance;
import com.onurbcd.eruservice.persistency.entity.Day;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.predicate.BalancePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BalanceRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BalanceService;
import com.onurbcd.eruservice.service.DayService;
import com.onurbcd.eruservice.service.SequenceService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.persistency.factory.SequenceParamFactory;
import com.onurbcd.eruservice.service.mapper.BalanceToEntityMapper;
import com.onurbcd.eruservice.service.validation.BalanceValidationService;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

/*
    TODO ao salvar ou atualizar, se o amount for diferente, precisa atualizar o balance do source, conforme o balanceType
    TODO analisar como vai ficar a lista de documents ao salvar, atualizar ou remover
 */
@Service
public class BalanceServiceImpl
        extends AbstractCrudService<Balance, BalanceDto, BalancePredicateBuilder, BalanceSaveDto>
        implements BalanceService {

    private final BalanceRepository repository;

    private final BalanceValidationService validationService;

    private final SequenceService sequenceService;

    private final DayService dayService;

    public BalanceServiceImpl(BalanceRepository repository, BalanceToEntityMapper toEntityMapper,
                              BalanceValidationService validationService,
                              @PrimeService(Domain.BALANCE_SEQUENCE) SequenceService sequenceService,
                              DayService dayService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, BalancePredicateBuilder.class);
        this.repository = repository;
        this.validationService = validationService;
        this.sequenceService = sequenceService;
        this.dayService = dayService;
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        validationService.validate((BalanceSaveDto) dto, (Balance) entity, id);
    }

    @Override
    public Balance fillValues(Dtoable dto, Entityable entity) {
        var balance = (Balance) super.fillValues(dto, entity);
        var current = (Balance) entity;
        balance.setName(null);
        balance.setSequence(getSequence(current, balance.getDay().getCalendarDate()));
        balance.getDay().setId(getDayId(balance, current));
        // TODO set documents
        return balance;
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return BalancePredicateBuilder.all((BalanceFilter) filter);
    }

    @Override
    public void delete(UUID id) {
        var balance = findByIdOrElseThrow(id);
        repository.deleteById(id);
        var sequenceParam = SequenceParamFactory.create(balance);
        // TODO atualizar o balance do source, conforme o balanceType
        sequenceService.updateNextSequences(sequenceParam);
    }

    @Override
    public void updateSequence(UUID id, Direction direction) {
        var balance = findByIdOrElseThrow(id);
        var sequenceParam = SequenceParamFactory.create(balance);
        sequenceService.swapSequence(sequenceParam, direction);
    }

    @Override
    public void swapPosition(UUID id, Short targetSequence) {
        var balance = findByIdOrElseThrow(id);
        var sequenceParam = SequenceParamFactory.create(balance, targetSequence);
        sequenceService.swapPosition(sequenceParam);
    }

    private Integer getDayId(Balance balance, @Nullable Balance current) {
        return Optional
                .ofNullable(current)
                .map(Balance::getDay)
                .map(Day::getId)
                .orElseGet(() -> dayService.createId(balance.getDay().getCalendarDate()));
    }

    private Short getSequence(Balance current, LocalDate calendarDate) {
        return Optional
                .ofNullable(current)
                .map(Balance::getSequence)
                .orElseGet(getNextSequence(calendarDate));
    }

    private Supplier<Short> getNextSequence(LocalDate calendarDate) {
        var sequenceParam = SequenceParamFactory.create(calendarDate);
        return () -> sequenceService.getNextSequence(sequenceParam);
    }
}
