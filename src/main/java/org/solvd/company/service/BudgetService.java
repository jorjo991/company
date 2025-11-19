package org.solvd.company.service;

import org.solvd.company.domain.budget.Budget;

import java.util.List;

public interface BudgetService {

    void create(Budget address, Long companyID);

    Budget getBudgetById(Long id);

    void updateBudget(Budget budget);

    void deleteBudget(Budget budget);

    List<Budget> getAllBudgets();
}
