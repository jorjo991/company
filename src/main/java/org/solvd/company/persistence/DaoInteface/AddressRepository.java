package org.solvd.company.persistence.DaoInteface;

import org.solvd.company.domain.company.Address;

import java.util.List;

public interface AddressRepository {

    void  create(Address address, Long officeId);
    void  update(Address t);
    Address  get( Long id);
    void  delete(Address address);
    List<Address> readAll();

}
