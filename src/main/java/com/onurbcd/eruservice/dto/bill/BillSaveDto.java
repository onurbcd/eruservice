package com.onurbcd.eruservice.dto.bill;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.dto.enums.DocumentType;
import com.onurbcd.eruservice.dto.enums.PaymentType;
import com.onurbcd.eruservice.dto.enums.ReferenceType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BillSaveDto extends PrimeSaveDto {

    @NotNull
    private LocalDate referenceDayCalendarDate;

    private LocalDate documentDateCalendarDate;

    @NotNull
    private LocalDate dueDateCalendarDate;

    @NotNull
    @DecimalMin(Constants.POSITIVE_AMOUNT_MIN)
    @DecimalMax(Constants.AMOUNT_MAX)
    private BigDecimal value;

    private LocalDate paymentDateCalendarDate; // close

    private UUID billDocumentId;

    private UUID receiptId; // close

    @Size(max = Constants.SIZE_250)
    private String observation; // open and close

    @Min(1)
    private Short installment;

    @NotNull
    private UUID billTypeId;

    @NotNull
    private DocumentType documentType;

    private PaymentType paymentType; // close

    @NotNull
    private UUID budgetId;

    private UUID sourceId; // close

    @NotNull
    private ReferenceType referenceType;
}
