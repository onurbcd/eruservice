package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.bill.BillCloseDto;
import com.onurbcd.eruservice.dto.bill.BillDto;
import com.onurbcd.eruservice.dto.bill.BillOpenDto;
import com.onurbcd.eruservice.persistency.entity.Bill;
import com.onurbcd.eruservice.persistency.entity.Day;
import com.onurbcd.eruservice.persistency.entity.Source;
import com.onurbcd.eruservice.persistency.predicate.BillPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BillDocumentService;
import com.onurbcd.eruservice.service.BillService;
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

    protected BillServiceImpl(BillRepository repository, BillOpenToEntityMapper toEntityMapper, DayService dayService,
                              EntityManager entityManager, BillDocumentService documentService) {

        super(repository, toEntityMapper, QueryType.CUSTOM, BillPredicateBuilder.class);
        this.repository = repository;
        this.toEntityMapper = toEntityMapper;
        this.dayService = dayService;
        this.entityManager = entityManager;
        this.documentService = documentService;
    }

    @Override
    public void openBill(BillOpenDto billOpenDto, MultipartFile multipartFile) {
        var bill = toEntityMapper.apply(billOpenDto);
        bill.setName(EruConstants.BOGUS_NAME);
        fillDay(billOpenDto.getReferenceDayCalendarDate(), bill::setReferenceDay);
        fillDay(billOpenDto.getDocumentDateCalendarDate(), bill::setDocumentDate);
        fillDay(billOpenDto.getDueDateCalendarDate(), bill::setDueDate);
        var billDocument = documentService.createDocument(BillDocParams.from(billOpenDto, multipartFile));
        bill.setBillDocument(billDocument);
        bill.setClosed(Boolean.FALSE);
        repository.save(bill);
    }

    @Override
    public void closeBill(UUID id, BillCloseDto billCloseDto, MultipartFile multipartFile) {
        var bill = getOrElseThrow(id);
        Action.checkIf(Boolean.FALSE.equals(bill.getClosed())).orElseThrow(Error.BILL_ALREADY_CLOSED);
        bill.setName(EruConstants.BOGUS_NAME);
        fillDay(billCloseDto.getPaymentDateCalendarDate(), bill::setPaymentDate);
        bill.setObservation(billCloseDto.getObservation());
        bill.setPaymentType(billCloseDto.getPaymentType());
        bill.setSource(entityManager.getReference(Source.class, billCloseDto.getSourceId()));
        var receipt = documentService.createDocument(BillDocParams.from(bill, multipartFile));
        bill.setReceipt(receipt);
        bill.setClosed(Boolean.TRUE);
        repository.save(bill);
        // TODO update budget and create balance
    }

    private void fillDay(@Nullable LocalDate localDateIn, Consumer<Day> dayConsumer) {
        Optional.ofNullable(localDateIn)
                .ifPresent(localDate -> {
                    var dayId = dayService.createId(localDate);
                    dayConsumer.accept(entityManager.getReference(Day.class, dayId));
                });
    }
}
