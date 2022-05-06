package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.persistency.entity.QSecret;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

public class SecretPredicateBuilder extends BasePredicateBuilder {

    private SecretPredicateBuilder() {
    }

    public static SecretPredicateBuilder of() {
        return new SecretPredicateBuilder();
    }

    public SecretPredicateBuilder search(@Nullable String search) {
        if (StringUtils.isNotBlank(search)) {
            builder().and(QSecret.secret.name.containsIgnoreCase(search)
                    .or(QSecret.secret.description.containsIgnoreCase(search))
                    .or(QSecret.secret.link.containsIgnoreCase(search))
                    .or(QSecret.secret.username.containsIgnoreCase(search)));
        }

        return this;
    }

    public SecretPredicateBuilder active(@Nullable Boolean active) {
        if (active != null) {
            builder().and(QSecret.secret.active.eq(active));
        }

        return this;
    }
}
