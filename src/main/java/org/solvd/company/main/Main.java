package org.solvd.company.main;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.company.Company;
import org.solvd.company.mybatis.impl.*;
import org.solvd.company.persistence.*;
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

    }
}