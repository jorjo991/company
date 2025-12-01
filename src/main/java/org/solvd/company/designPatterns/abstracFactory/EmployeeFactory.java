package org.solvd.company.designPatterns.abstracFactory;

import org.solvd.company.domain.company.Address;
import org.solvd.company.domain.employees.Employee;
import org.solvd.company.domain.person.Person;

public class EmployeeFactory implements AbstractPersonFactory {

    @Override
    public Person createPerson() {
        return new Employee();
    }

    @Override
    public Address createAddress() {
        return new Address();
    }
}
