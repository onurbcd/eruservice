package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface BalanceService extends CrudService, Sequenceable {

    @Transactional
    void save(BalanceSaveDto saveDto, @Nullable MultipartFile[] multipartFiles, @Nullable UUID id);
}
