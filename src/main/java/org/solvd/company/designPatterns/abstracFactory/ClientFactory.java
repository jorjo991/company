package org.solvd.company.designPatterns.abstracFactory;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Address;
import org.solvd.company.domain.person.Person;

public class ClientFactory implements AbstractPersonFactory {

    @Override
    public Person createPerson() {
        return new Client();
    }

    @Override
    public Address createAddress() {
        return new Address();
    }
}
