package org.solvd.company.designPatterns.facade;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.employees.Employee;
import org.solvd.company.domain.person.Person;

public class PersonFacade {

    private Person employee;
    private Person client;

    public PersonFacade() {
        employee = new Employee();
        client = new Client();
    }

    public void createEmployee() {
        System.out.println("Creating Employee");
    }

    public void createClient() {
        System.out.println("Creating Client");
    }

}
