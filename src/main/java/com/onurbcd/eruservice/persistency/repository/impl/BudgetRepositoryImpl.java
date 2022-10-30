package com.onurbcd.eruservice.persistency.repository.impl;

import com.onurbcd.eruservice.dto.budget.BudgetDto;
import com.onurbcd.eruservice.persistency.entity.QBudget;
import com.onurbcd.eruservice.persistency.predicate.BudgetPredicateBuilder;
import com.onurbcd.eruservice.persistency.repository.CustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class BudgetRepositoryImpl implements CustomRepository<BudgetDto> {

    private static final QBean<BudgetDto> COLUMNS = Projections.bean(BudgetDto.class, QBudget.budget.createdDate,
            QBudget.budget.lastModifiedDate, QBudget.budget.id, QBudget.budget.name, QBudget.budget.active,
            QBudget.budget.sequence, QBudget.budget.refYear, QBudget.budget.refMonth,
            QBudget.budget.billType.id.as("billTypeId"), QBudget.budget.billType.name.as("billTypeName"),
            QBudget.budget.amount, QBudget.budget.paid);

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<BudgetDto> getAll(Predicate predicate, Pageable pageable) {
        var content = new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QBudget.budget)
                .innerJoin(QBudget.budget.billType)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderBy(pageable.getSort(), QBudget.budget.getMetadata().getName()))
                .fetch();

        final var totalSupplier = content.size() < pageable.getPageSize()
                ? content.size()
                : new JPAQuery<>(manager).from(QBudget.budget).innerJoin(QBudget.budget.billType).where(predicate)
                .fetchCount();

        return PageableExecutionUtils.getPage(content, pageable, () -> totalSupplier);
    }

    @Override
    public List<BudgetDto> getAll(Predicate predicate) {
        return new JPAQuery<>(manager)
                .select(COLUMNS)
                .from(QBudget.budget)
                .innerJoin(QBudget.budget.billType)
                .where(predicate)
                .fetch();
    }

    @Override
    public BudgetDto getSingle(UUID id) {
        var predicate = BudgetPredicateBuilder.id(id);

        return new JPAQuery<>(manager).select(COLUMNS).from(QBudget.budget).innerJoin(QBudget.budget.billType)
                .where(predicate).fetchFirst();
    }
}
