package com.onurbcd.eruservice.dto.billtype;

import com.onurbcd.eruservice.dto.PrimeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BillTypeDto extends PrimeDto {

    private String path;

    private UUID categoryId;

    private String categoryName;
}
