package com.onurbcd.eruservice.service;

import java.math.BigDecimal;

public interface SourceService extends CrudService {

    BigDecimal getUsableBalanceSum();
}
