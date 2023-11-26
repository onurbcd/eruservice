package com.onurbcd.eruservice.service.resource;

import com.onurbcd.eruservice.dto.bill.BillCloseDto;
import com.onurbcd.eruservice.persistency.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillBalanceParams {

    private BillCloseDto billCloseDto;

    private Bill bill;

    private UUID categoryId;
}
