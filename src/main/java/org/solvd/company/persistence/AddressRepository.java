package org.solvd.company.persistence;

import org.solvd.company.domain.company.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    void create(Address address, Long officeId);

    void update(Address t);

    Optional<Address> get(Long id);

    void delete(Address address);

    List<Address> readAll();

}
