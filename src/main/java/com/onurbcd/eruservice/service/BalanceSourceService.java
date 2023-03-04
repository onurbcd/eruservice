package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.persistency.entity.Balance;

import java.math.BigDecimal;

public interface BalanceSourceService {

    void save(Balance newBalance, BigDecimal currentAmount);

    void delete(Balance balance);
}
