package com.onurbcd.eruservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtil {

    @Nullable
    public static <T, R> R getValue(Collection<T> collection, Predicate<? super T> predicate,
                                    Function<? super T, ? extends R> func) {

        return collection.stream().filter(predicate).findFirst().map(func).orElse(null);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
}
