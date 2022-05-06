package com.onurbcd.eruservice.service.filter;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Setter
public class SecretFilter extends AbstractFilterable implements Serializable {

    @Serial
    private static final long serialVersionUID = 853187453830398981L;

    private String search;

    public String getSearch() {
        return search != null ? search.trim() : null;
    }
}
