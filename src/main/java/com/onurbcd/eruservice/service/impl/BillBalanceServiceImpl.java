package com.onurbcd.eruservice.service.impl;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.balance.BalanceSaveDto;
import com.onurbcd.eruservice.dto.enums.BalanceType;
import com.onurbcd.eruservice.dto.enums.ReferenceType;
import com.onurbcd.eruservice.persistency.entity.Balance;
import com.onurbcd.eruservice.persistency.entity.Bill;
import com.onurbcd.eruservice.service.BalanceService;
import com.onurbcd.eruservice.service.BillBalanceService;
import com.onurbcd.eruservice.service.resource.BillBalanceParams;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BillBalanceServiceImpl implements BillBalanceService {

    private final BalanceService balanceService;

    @Override
    public Balance saveBalance(BillBalanceParams params) {
        var balanceSaveDto = createBalanceSaveDto(params);
        return balanceService.save(balanceSaveDto);
    }

    private BalanceSaveDto createBalanceSaveDto(BillBalanceParams params) {
        var balanceSaveDto = new BalanceSaveDto();
        balanceSaveDto.setName(EruConstants.BOGUS_NAME);
        balanceSaveDto.setActive(Boolean.TRUE);
        balanceSaveDto.setSequence(Short.MAX_VALUE);
        balanceSaveDto.setDayCalendarDate(params.getBillCloseDto().getPaymentDateCalendarDate());
        balanceSaveDto.setSourceId(params.getBillCloseDto().getSourceId());
        balanceSaveDto.setCategoryId(params.getCategoryId());
        balanceSaveDto.setAmount(params.getBill().getValue());
        balanceSaveDto.setCode(EruConstants.BILL_CLOSE_CODE);
        balanceSaveDto.setDescription(createDescription(params));
        balanceSaveDto.setBalanceType(BalanceType.OUTCOME);
        balanceSaveDto.setDocumentsIds(null);
        return balanceSaveDto;
    }

    private String createDescription(BillBalanceParams params) {
        return new StringBuilder()
                .append("Pagamento efetuado por ")
                .append(params.getBillCloseDto().getPaymentType().getCode())
                .append(": ")
                .append(params.getBill().getDocumentType().getCode())
                .append(StringUtils.SPACE)
                .append(params.getBill().getBudget().getName())
                .append(StringUtils.SPACE)
                .append("referente a ")
                .append(createReference(params.getBill()))
                .toString();
    }

    private String createReference(Bill bill) {
        var pattern = ReferenceType.YEAR == bill.getReferenceType() ?
                EruConstants.YEAR_PATTERN :
                EruConstants.MONTH_YEAR_PATTERN;

        return bill.getReferenceDay().getCalendarDate().format(DateTimeFormatter.ofPattern(pattern));
    }
}
