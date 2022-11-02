package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.dto.incomesource.IncomeSourceDto;
import com.onurbcd.eruservice.dto.incomesource.IncomeSourceSaveDto;
import com.onurbcd.eruservice.persistency.entity.IncomeSource;
import com.onurbcd.eruservice.persistency.predicate.IncomeSourcePredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.IncomeSourceRepository;
import com.onurbcd.eruservice.service.AbstractCrudService;
import com.onurbcd.eruservice.service.IncomeSourceService;
import com.onurbcd.eruservice.service.enums.QueryType;
import com.onurbcd.eruservice.service.mapper.IncomeSourceToDtoMapper;
import com.onurbcd.eruservice.service.mapper.IncomeSourceToEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class IncomeSourceServiceImpl
        extends AbstractCrudService<IncomeSource, IncomeSourceDto, IncomeSourcePredicateBuilder, IncomeSourceSaveDto>
        implements IncomeSourceService {

    public IncomeSourceServiceImpl(IncomeSourceRepository repository, IncomeSourceToDtoMapper toDtoMapper,
                                   IncomeSourceToEntityMapper toEntityMapper) {

        super(repository, toDtoMapper, toEntityMapper, QueryType.JPA, IncomeSourcePredicateBuilder.class);
    }
}
