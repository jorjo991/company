package org.solvd.company.persistence.DaoInteface;

import org.solvd.company.domain.budget.Budget;

import java.util.List;

public interface BudgetRepository {

    void  create(Budget budget,Long companyId);
    void  update(Budget t);
    Budget  get( Long id);
    void  delete(Budget budget);
    List<Budget> readAll();

}
