package org.solvd.company.domain.person;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.employees.Employee;

public class PersonFactory {

    private Person person;

    public static Person createPerson(String type) {
        if (type.equals("Employee")) {
            return new Employee();
        } else if (type.equals("Client")) {
            return new Client();
        }
        return null;
    }
}
