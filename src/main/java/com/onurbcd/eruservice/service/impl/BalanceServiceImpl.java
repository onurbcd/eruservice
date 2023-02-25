package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
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
import com.onurbcd.eruservice.service.BalanceDocumentService;
import com.onurbcd.eruservice.service.BalanceService;
import com.onurbcd.eruservice.service.DayService;
import com.onurbcd.eruservice.service.SequenceService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.persistency.factory.SequenceParamFactory;
import com.onurbcd.eruservice.service.mapper.BalanceToEntityMapper;
import com.onurbcd.eruservice.service.resource.CreateBalance;
import com.onurbcd.eruservice.service.validation.BalanceValidationService;
import com.onurbcd.eruservice.util.CollectionUtil;
import com.querydsl.core.types.Predicate;
import jakarta.persistence.EntityManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class BalanceServiceImpl
        extends AbstractCrudService<Balance, BalanceDto, BalancePredicateBuilder, BalanceSaveDto>
        implements BalanceService {

    private final BalanceRepository repository;

    private final BalanceValidationService validationService;

    private final SequenceService sequenceService;

    private final DayService dayService;

    private final BalanceDocumentService balanceDocumentService;

    private final EntityManager entityManager;

    public BalanceServiceImpl(BalanceRepository repository, BalanceToEntityMapper toEntityMapper,
                              BalanceValidationService validationService,
                              @PrimeService(Domain.BALANCE_SEQUENCE) SequenceService sequenceService,
                              DayService dayService, BalanceDocumentService balanceDocumentService,
                              EntityManager entityManager) {

        super(repository, toEntityMapper, QueryType.CUSTOM, BalancePredicateBuilder.class);
        this.repository = repository;
        this.validationService = validationService;
        this.sequenceService = sequenceService;
        this.dayService = dayService;
        this.balanceDocumentService = balanceDocumentService;
        this.entityManager = entityManager;
    }

    @Override
    public void save(BalanceSaveDto saveDto, MultipartFile[] multipartFiles, UUID id) {
        var currentBalance = id != null ? repository.get(id).orElse(null) : null;
        validate(saveDto, currentBalance, id);
        var createBalance = fillValues(saveDto, multipartFiles, currentBalance);
        repository.saveAndFlush(createBalance.getBalance());
        balanceDocumentService.deleteDocuments(createBalance.getDeleteDocuments());
        // TODO atualizar o balance do source, conforme o balanceType
    }

    @Override
    public void validate(Dtoable dto, Entityable entity, UUID id) {
        validationService.validate((BalanceSaveDto) dto, (Balance) entity, id);
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        return BalancePredicateBuilder.all((BalanceFilter) filter);
    }

    @Override
    public BalanceDto getById(UUID id) {
        var balanceDto = (BalanceDto) super.getById(id);
        var documentsIds = repository.getDocumentsIds(id);
        balanceDto.setDocumentsIds(CollectionUtil.isEmpty(documentsIds) ? null : documentsIds);
        return balanceDto;
    }

    @Override
    public void delete(UUID id) {
        var balance = getOrElseThrow(id);
        var documents = repository.getDocuments(id);
        repository.deleteById(id);
        var sequenceParam = SequenceParamFactory.create(balance);
        sequenceService.updateNextSequences(sequenceParam);
        balanceDocumentService.deleteDocuments(documents);
        // TODO atualizar o balance do source, conforme o balanceType
    }

    @Override
    public void updateSequence(UUID id, Direction direction) {
        var balance = getOrElseThrow(id);
        var sequenceParam = SequenceParamFactory.create(balance);
        sequenceService.swapSequence(sequenceParam, direction);
    }

    @Override
    public void swapPosition(UUID id, Short targetSequence) {
        var balance = getOrElseThrow(id);
        var sequenceParam = SequenceParamFactory.create(balance, targetSequence);
        sequenceService.swapPosition(sequenceParam);
    }

    private CreateBalance fillValues(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles,
                                     @Nullable Balance currentBalance) {

        var balance = (Balance) super.fillValues(saveDto, currentBalance);
        var id = Optional.ofNullable(currentBalance).map(Balance::getId).orElse(null);
        var createDocument = balanceDocumentService.createDocuments(saveDto, multipartFiles, id);
        balance.setDocuments(createDocument.getSaveDocuments());
        balance.setName(EruConstants.BALANCE_NAME);
        balance.setSequence(getSequence(currentBalance, saveDto.getDayCalendarDate()));
        var dayId = getDayId(saveDto, currentBalance);
        balance.setDay(entityManager.getReference(Day.class, dayId));
        return CreateBalance.builder().balance(balance).deleteDocuments(createDocument.getDeleteDocuments()).build();
    }

    private Short getSequence(@Nullable Balance current, LocalDate calendarDate) {
        return Optional
                .ofNullable(current)
                .map(Balance::getSequence)
                .orElseGet(getNextSequence(calendarDate));
    }

    private Supplier<Short> getNextSequence(LocalDate calendarDate) {
        var sequenceParam = SequenceParamFactory.create(calendarDate);
        return () -> sequenceService.getNextSequence(sequenceParam);
    }

    private Integer getDayId(BalanceSaveDto saveDto, @Nullable Balance current) {
        return Optional
                .ofNullable(current)
                .map(Balance::getDay)
                .map(Day::getId)
                .orElseGet(() -> dayService.createId(saveDto.getDayCalendarDate()));
    }
}
