package com.onurbcd.eruservice.dto.bill;

import com.onurbcd.eruservice.config.EruConstants;
import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.PrimeSaveDto;
import com.onurbcd.eruservice.dto.enums.DocumentType;
import com.onurbcd.eruservice.dto.enums.ReferenceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public class BillOpenDto extends PrimeSaveDto {

    public BillOpenDto() {
        super(EruConstants.BOGUS_NAME, Boolean.TRUE);
    }

    @NotNull
    private LocalDate referenceDayCalendarDate;

    private LocalDate documentDateCalendarDate;

    @NotNull
    private LocalDate dueDateCalendarDate;

    @Size(max = Constants.SIZE_250)
    private String observation;

    @Min(1)
    private Short installment;

    @NotNull
    private DocumentType documentType;

    @NotNull
    private UUID budgetId;

    @NotNull
    private ReferenceType referenceType;
}
