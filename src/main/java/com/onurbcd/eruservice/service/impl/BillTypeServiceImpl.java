package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.predicate.BillTypePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.BillTypeRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.BillTypeService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.BillTypeToDtoMapper;
import com.onurbcd.eruservice.service.mapper.BillTypeToEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class BillTypeServiceImpl extends AbstractCrudService<BillType, BillTypeDto, BillTypePredicateBuilder, BillTypeDto>
        implements BillTypeService {

    public BillTypeServiceImpl(BillTypeRepository repository, BillTypeToDtoMapper toDtoMapper,
                               BillTypeToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA, BillTypePredicateBuilder.class);
    }
}
