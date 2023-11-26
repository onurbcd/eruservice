package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.billtype.BillTypeValuesDto;

import java.util.UUID;

public interface BillTypeService extends CrudService {

    BillTypeValuesDto getValues(UUID id);
}
