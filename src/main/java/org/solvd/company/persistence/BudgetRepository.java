package org.solvd.company.persistence;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.budget.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository {

    void create(@Param("budget") Budget budget, @Param("companyId") Long companyId);

    void update(Budget t);

    Optional<Budget> get(Long id);

    void delete(Long id);

    List<Budget> readAll();

}
