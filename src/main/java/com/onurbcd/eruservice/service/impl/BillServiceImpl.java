package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.bill.BillCloseDto;
import com.onurbcd.eruservice.dto.bill.BillDto;
import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.dto.budget.BudgetPatchDto;
import com.onurbcd.eruservice.persistency.entity.Bill;
import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.entity.Day;
import com.onurbcd.eruservice.persistency.entity.Source;
import com.onurbcd.eruservice.persistency.predicate.BillPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BillDocumentService;
import com.onurbcd.eruservice.service.BillService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.BudgetService;
import com.onurbcd.eruservice.service.DayService;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.BillOpenToEntityMapper;
import com.onurbcd.eruservice.service.resource.BillDocParams;
import com.onurbcd.eruservice.service.validation.Action;
import jakarta.persistence.EntityManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class BillServiceImpl
        extends AbstractCrudService<Bill, BillDto, BillPredicateBuilder, BillOpenDto>
        implements BillService {

    private final BillRepository repository;

    private final BillOpenToEntityMapper toEntityMapper;

    private final DayService dayService;

    private final EntityManager entityManager;

    private final BillDocumentService documentService;

    private final BudgetService budgetService;

    private final BillTypeService billTypeService;

    protected BillServiceImpl(BillRepository repository, BillOpenToEntityMapper toEntityMapper, DayService dayService,
                              EntityManager entityManager, BillDocumentService documentService,
                              BudgetService budgetService, BillTypeService billTypeService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, BillPredicateBuilder.class);
        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.dayService = dayService;
        this.entityManager = entityManager;
        this.documentService = documentService;
        this.budgetService = budgetService;
        this.billTypeService = billTypeService;
    }

    @Override
    public UUID openBill(BillOpenDto billOpenDto, MultipartFile multipartFile) {
        var bill = toEntityMapper.apply(billOpenDto);
        var budgetValues = budgetService.getBudgetValues(billOpenDto.getBudgetId());
        Action.checkIf(Boolean.FALSE.equals(budgetValues.paid())).orElseThrow(Error.BILL_ALREADY_PAID);

        var billDocParams = BillDocParams
                .builder()
                .path(budgetValues.path())
                .referenceDayCalendarDate(billOpenDto.getReferenceDayCalendarDate())
                .multipartFile(multipartFile)
                .documentType(billOpenDto.getDocumentType())
                .referenceType(billOpenDto.getReferenceType())
                .build();

        var billDocument = documentService.createDocument(billDocParams);

        bill.setName(EruConstants.BOGUS_NAME);
        fillDay(billOpenDto.getReferenceDayCalendarDate(), bill::setReferenceDay);
        fillDay(billOpenDto.getDocumentDateCalendarDate(), bill::setDocumentDate);
        fillDay(billOpenDto.getDueDateCalendarDate(), bill::setDueDate);
        bill.setValue(budgetValues.amount());
        bill.setBillDocument(billDocument);
        bill.setBillType(entityManager.getReference(BillType.class, budgetValues.billTypeId()));
        bill.setClosed(Boolean.FALSE);

        bill = repository.save(bill);
        return bill.getId();
    }

    @Override
    public void closeBill(UUID id, BillCloseDto billCloseDto, MultipartFile multipartFile) {
        var bill = getOrElseThrow(id);
        Action.checkIf(Boolean.FALSE.equals(bill.getClosed())).orElseThrow(Error.BILL_ALREADY_CLOSED);
        var path = billTypeService.getPathById(bill.getBillType().getId());

        var billDocParams = BillDocParams
                .builder()
                .path(path)
                .referenceDayCalendarDate(bill.getReferenceDay().getCalendarDate())
                .multipartFile(multipartFile)
                .documentType(bill.getDocumentType())
                .referenceType(bill.getReferenceType())
                .build();

        var receipt = documentService.createDocument(billDocParams);

        bill.setName(EruConstants.BOGUS_NAME);
        fillDay(billCloseDto.getPaymentDateCalendarDate(), bill::setPaymentDate);
        bill.setObservation(billCloseDto.getObservation());
        bill.setPaymentType(billCloseDto.getPaymentType());
        bill.setSource(entityManager.getReference(Source.class, billCloseDto.getSourceId()));
        bill.setReceipt(receipt);
        bill.setClosed(Boolean.TRUE);

        repository.save(bill);
        budgetService.update(BudgetPatchDto.of(Boolean.TRUE), bill.getBudget().getId());

        // TODO create balance
    }

    private void fillDay(@Nullable LocalDate localDateIn, Consumer<Day> dayConsumer) {
        Optional.ofNullable(localDateIn)
                .ifPresent(localDate -> {
                    var dayId = dayService.createId(localDate);
                    dayConsumer.accept(entityManager.getReference(Day.class, dayId));
                });
    }
}
