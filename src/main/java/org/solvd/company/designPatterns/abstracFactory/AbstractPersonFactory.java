package org.solvd.company.designPatterns.abstracFactory;

import org.solvd.company.domain.company.Address;
import org.solvd.company.domain.person.Person;

public interface AbstractPersonFactory {

    Person createPerson();

    Address createAddress();

}
