package com.onurbcd.eruservice.dto.bill;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.dto.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BillCloseDto extends PrimeSaveDto {

    public BillCloseDto() {
        setName(EruConstants.BOGUS_NAME);
        setActive(Boolean.TRUE);
    }

    @NotNull
    private LocalDate paymentDateCalendarDate;

    @Size(max = Constants.SIZE_250)
    private String observation;

    @NotNull
    private PaymentType paymentType;

    @NotNull
    private UUID sourceId;
}
