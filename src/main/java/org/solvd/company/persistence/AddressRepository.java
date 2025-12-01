package org.solvd.company.persistence;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.company.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    void create(@Param("address") Address address, @Param("officeId") Long officeId);

    void update(Address t);

    Optional<Address> get(Long id);

    void delete(Long id);

    List<Address> readAll();

}
