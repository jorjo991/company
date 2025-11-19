package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.BudgetRepository;
import org.solvd.company.persistence.impl.BudgetRepositoryImp;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.BudgetService;

import java.util.List;

public class BudgetServiceImp implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImp() {
        this.budgetRepository = new BudgetRepositoryImp();
    }

    @Override
    public void create(Budget budget, Long companyID) {
        budgetRepository.create(budget, companyID);
    }

    @Override
    public Budget getBudgetById(Long id) {

        return budgetRepository.get(id).orElseThrow(() -> new RuntimeException("Budget is not presented"));
    }

    @Override
    public void updateBudget(Budget budget) {
        budgetRepository.update(budget);
    }

    @Override
    public void deleteBudget(Budget budget) {
        budgetRepository.delete(budget);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.readAll();
    }
}
