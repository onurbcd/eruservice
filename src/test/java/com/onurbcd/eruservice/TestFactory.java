package com.onurbcd.eruservice;

import com.onurbcd.eruservice.persistency.entity.BillType;
import com.onurbcd.eruservice.persistency.entity.Budget;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.onurbcd.eruservice.TestConstants.MONTH;
import static com.onurbcd.eruservice.TestConstants.NAME;
import static com.onurbcd.eruservice.TestConstants.PATH;
import static com.onurbcd.eruservice.TestConstants.SEQUENCE;
import static com.onurbcd.eruservice.TestConstants.YEAR;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestFactory {

    public static Budget createBudget() {
        return Budget
                .builder()
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .id(UUID.randomUUID())
                .name(NAME)
                .active(Boolean.TRUE)
                .sequence(SEQUENCE)
                .refYear(YEAR)
                .refMonth(MONTH)
                .billType(createBillType())
                .amount(BigDecimal.TEN)
                .paid(Boolean.FALSE)
                .build();
    }

    public static BillType createBillType() {
        return BillType
                .builder()
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .id(UUID.randomUUID())
                .name(NAME)
                .active(Boolean.TRUE)
                .path(PATH)
                .build();
    }
}
