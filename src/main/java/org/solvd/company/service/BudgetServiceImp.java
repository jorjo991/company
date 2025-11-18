package org.solvd.company.service;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.persistence.impl.BudgetRepositoryImp;
import org.solvd.company.service.interfcae.BudgetService;

import java.util.List;

public class BudgetServiceImp implements BudgetService {

    private final BudgetRepositoryImp budgetRepositoryImp;

    public BudgetServiceImp(BudgetRepositoryImp budgetRepositoryImp) {

        this.budgetRepositoryImp = budgetRepositoryImp;
    }

    @Override
    public void create(Budget budget, Long companyID) {
        budgetRepositoryImp.create(budget, companyID);
    }

    @Override
    public Budget getBudgetById(Long id) {
        Budget budget = budgetRepositoryImp.get(id);
        if (budget == null) {
            throw new RuntimeException("Budget not found with id = " + id);
        }
        return budget;
    }

    @Override
    public void updateBudget(Budget budget) {
        budgetRepositoryImp.update(budget);
    }

    @Override
    public void deleteBudget(Budget budget) {
        budgetRepositoryImp.delete(budget);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepositoryImp.readAll();
    }
}
