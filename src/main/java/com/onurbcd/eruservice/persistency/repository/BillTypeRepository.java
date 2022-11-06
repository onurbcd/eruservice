package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.billtype.BillTypeDto;
import com.onurbcd.eruservice.persistency.entity.BillType;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypeRepository extends MiddlewareRepository<BillType, BillTypeDto> {
}
