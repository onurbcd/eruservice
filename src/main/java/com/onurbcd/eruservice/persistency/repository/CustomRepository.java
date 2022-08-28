package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.Dtoable;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface CustomRepository<D extends Dtoable> {

    Page<D> getAll(Predicate predicate, Pageable pageable);

    List<D> getAll(Predicate predicate);

    @Nullable
    D getSingle(UUID id);

    default OrderSpecifier<?>[] orderBy(Sort sort, String alias) {
        var size = (int) sort.stream().count();
        var result = new OrderSpecifier[size];
        var index = 0;

        for (Sort.Order order : sort) {
            var pathBuilder = new PathBuilder<>(Object.class, alias);
            var target = pathBuilder.get(order.getProperty());
            var orderSpecifier = new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, target);
            result[index] = orderSpecifier;
            index++;
        }

        return result;
    }
}
