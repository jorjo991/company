package org.solvd.company.persistence;

import org.solvd.company.domain.budget.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository {

    void create(Budget budget, Long companyId);

    void update(Budget t);

    Optional<Budget> get(Long id);

    void delete(Budget budget);

    List<Budget> readAll();

}
