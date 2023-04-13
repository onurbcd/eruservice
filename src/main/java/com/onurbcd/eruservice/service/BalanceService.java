package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.balance.BalanceSumDto;
import com.onurbcd.eruservice.dto.filter.BalanceFilter;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

public interface BalanceService extends CrudService, Sequenceable {

    @Transactional
    void save(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles, @Nullable UUID id);

    Set<BalanceSumDto> getSum(BalanceFilter filter);
}
