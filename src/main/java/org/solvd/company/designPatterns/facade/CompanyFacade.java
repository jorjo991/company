package org.solvd.company.designPatterns.facade;

import org.solvd.company.domain.budget.Budget;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyFacade {

    private CompanyService companyService;
    private ClientService clientService;

    public void createFullCompany() {
        companyService = new CompanyService();
        clientService = new ClientService();
        companyService.createCompany("solvd", new Budget(), new ArrayList<>(), new ArrayList<>());
        clientService.createClient("John", "eamil", "done", LocalDate.now());
    }
}
