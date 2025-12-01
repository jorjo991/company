package org.solvd.company.main;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Company;
import org.solvd.company.domain.employees.Employee;
import org.solvd.company.designPatterns.facade.CompanyFacade;
import org.solvd.company.persistence.*;
import org.solvd.company.persistence.impl.*;
import org.xml.sax.SAXException;

import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SAXException {
        LaptopRepository laptopRepository = new LaptopRepositoryMyBatisImp();
        System.out.println(laptopRepository.get(2L));
        System.out.println(laptopRepository.readAll());
        System.out.println("-------------------");

        BudgetRepository budgetRepository = new BudgetRepositoryMyBatisImp();
        Optional<Budget> budget = budgetRepository.get(1L);
        System.out.println(budget);
        System.out.println(budgetRepository.readAll());
        System.out.println("-------------------");

        AddressRepository addressRepository = new AddressRepositoryMyBatisImp();
        System.out.println(addressRepository.get(1L));
        System.out.println(addressRepository.readAll());
        System.out.println("-------------------");

        ClientsRepository clientsRepository = new ClientRepositoryMyBatisImp();
        System.out.println(clientsRepository.get(2L));
        System.out.println(clientsRepository.readAll());
        System.out.println("-------------------");

        CompanyRepository companyRepository = new CompanyRepositoryMyBatisImp();
        System.out.println(companyRepository.get(1L));
        System.out.println(companyRepository.readAll());

        Employee employee = new Employee.Builder().id(15L).name("John Doe").surname("Smith").age(30).email("email").build();
        System.out.println(employee);

        Company company = new Company();
        company.setId(1L);
        company.setName("Tech Solutions");

        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Acme");
        client1.setSurname("Corp");

        Client client2 = new Client();
        client2.setName("Global");
        client2.setSurname("Enterprises");

        company.registerObserver(client1);
        company.registerObserver(client2);

        company.setInformation("New product launch next month!");

        // facade pattern usage

        CompanyFacade companyFacade = new CompanyFacade();
        companyFacade.createFullCompany();

    }
}