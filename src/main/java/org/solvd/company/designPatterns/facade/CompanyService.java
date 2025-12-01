package org.solvd.company.designPatterns.facade;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Company;
import org.solvd.company.domain.department.Department;

import java.util.List;

public class CompanyService {

    private Company company;

    public CompanyService() {
    }

    public void createCompany(String name, Budget budget, List<Department> departments, List<Client> clients) {
        {
            this.company = new Company();
            this.company.setName(name);
            this.company.setBudget(budget);
            this.company.setDepartments(departments);
            this.company.setClients(clients);

        }
    }

}
