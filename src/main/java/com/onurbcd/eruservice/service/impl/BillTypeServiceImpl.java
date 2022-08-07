package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.entity.Entityable;
import com.onurbcd.eruservice.persistency.predicate.BillTypePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillTypeRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.filter.BillTypeFilter;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.BillTypeToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BillTypeToEntityMapper;
import com.onurbcd.eruservice.service.validation.Action;
import com.querydsl.core.types.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillTypeServiceImpl extends AbstractCrudService<BillType, BillTypeDto> implements BillTypeService {

    private final BillTypeToEntityMapper toEntityMapper;

    public BillTypeServiceImpl(BillTypeRepository repository, BillTypeToDtoMapper toDtoMapper,
                               BillTypeToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper);
        this.toEntityMapper = toEntityMapper;
    }

    @Override
    public void validate(Dtoable dto, @Nullable Entityable entity, @Nullable UUID id) {
        Action.checkIf(id == null || entity != null).orElseThrowNotFound(id);
    }

    @Override
    public Entityable fillValues(Dtoable dto, Entityable entity) {
        var billTypeDto = (BillTypeDto) dto;
        var currentBillType = (BillType) entity;
        var billType = toEntityMapper.apply(billTypeDto);
        billType.setId(currentBillType != null ? currentBillType.getId() : null);

        if (currentBillType != null) {
            billType.setCreatedDate(currentBillType.getCreatedDate());
        }

        return billType;
    }

    @Override
    protected Predicate getPredicate(Filterable filter) {
        var billTypeFilter = (BillTypeFilter) filter;

        return BillTypePredicateBuilder
                .of()
                .name(billTypeFilter.getSearch())
                .active(billTypeFilter.isActive())
                .build();
    }
}
