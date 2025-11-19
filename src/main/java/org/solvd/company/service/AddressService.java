package org.solvd.company.service;

import org.solvd.company.domain.company.Address;

import java.util.List;

public interface AddressService {

    void create(Address address, Long OfficeID);

    Address getAddressById(Long id);

    void updateAddress(Address address);

    void deleteAddress(Address address);

    List<Address> getAllAddresses();
}
