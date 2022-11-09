package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.annotations.PrimeService;
import com.onurbcd.eruservice.config.enums.Domain;
import com.onurbcd.eruservice.dto.billtype.BillTypeDto;
import com.onurbcd.eruservice.dto.billtype.BillTypeSaveDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.predicate.BillTypePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillTypeRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.CrudService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.BillTypeToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BillTypeToEntityMapper;

@PrimeService(Domain.BILL_TYPE)
public class BillTypeService
        extends AbstractCrudService<BillType, BillTypeDto, BillTypePredicateBuilder, BillTypeSaveDto>
        implements CrudService {

    public BillTypeService(BillTypeRepository repository, BillTypeToDtoMapper toDtoMapper,
                           BillTypeToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA, BillTypePredicateBuilder.class);
    }
}
