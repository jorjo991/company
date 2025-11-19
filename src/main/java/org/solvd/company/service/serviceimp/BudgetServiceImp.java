package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.impl.BudgetRepositoryImp;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.BudgetService;
import org.solvd.company.service.ClientService;

import java.util.List;

public class BudgetServiceImp implements BudgetService {

    private final BudgetRepositoryImp budgetRepositoryImp;

    public BudgetServiceImp(BudgetRepositoryImp budgetRepositoryImp) {
        this.budgetRepositoryImp = budgetRepositoryImp;
    }

    @Override
    public void create(Budget budget, Long companyID) {
        ClientServiceImp clientServiceImp = new ClientServiceImp(new ClientsRepositoryImp());
        clientServiceImp.create(new Client(), companyID);
        budgetRepositoryImp.create(budget, companyID);
    }

    @Override
    public Budget getBudgetById(Long id) {

        return budgetRepositoryImp.get(id).orElseThrow(() -> new RuntimeException("Budget is not presented"));
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
