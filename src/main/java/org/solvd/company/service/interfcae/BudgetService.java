package org.solvd.company.service.interfcae;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.company.Address;

import java.util.List;

public interface BudgetService {

    void create(Budget address, Long companyID);

    Budget getBudgetById(Long id);

    void updateBudget(Budget budget);

    void deleteBudget(Budget budget);

    List<Budget> getAllBudgets();
}
