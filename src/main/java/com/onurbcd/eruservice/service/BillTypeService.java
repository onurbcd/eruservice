package com.onurbcd.eruservice.service;

import java.util.UUID;

public interface BillTypeService extends CrudService {

    String getPathById(UUID id);
}
