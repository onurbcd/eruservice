package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID>, QuerydslPredicateExecutor<Budget> {
}