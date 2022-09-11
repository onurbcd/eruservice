package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.predicate.BillTypePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillTypeRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.filter.BillTypeFilter;
import com.onurbcd.eruservice.service.filter.Filterable;
import com.onurbcd.eruservice.service.mapper.BillTypeToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BillTypeToEntityMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

@Service
public class BillTypeServiceImpl extends AbstractCrudService<BillType, BillTypeDto> implements BillTypeService {

    public BillTypeServiceImpl(BillTypeRepository repository, BillTypeToDtoMapper toDtoMapper,
                               BillTypeToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA);
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
