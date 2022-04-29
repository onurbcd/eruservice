package com.onurbcd.eruservice.service.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class SecretFilter implements Filterable, Serializable {

    @Serial
    private static final long serialVersionUID = 853187453830398981L;

    private String search;
}
