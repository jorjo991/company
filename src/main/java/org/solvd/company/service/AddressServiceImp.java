package org.solvd.company.service;

import org.solvd.company.domain.company.Address;
import org.solvd.company.persistence.impl.AddressRepositoryImp;
import org.solvd.company.service.interfcae.AddressService;

import java.util.List;

public class AddressServiceImp implements AddressService {

    private final AddressRepositoryImp addressRepositoryImp;

    public AddressServiceImp(AddressRepositoryImp addressRepositoryImp) {
        this.addressRepositoryImp = addressRepositoryImp;
    }

    @Override
    public void create(Address address, Long id) {
        addressRepositoryImp.create(address, id);
    }

    @Override
    public Address getAddressById(Long id) {
        Address address = addressRepositoryImp.get(id);
        if (address == null) {
            throw new RuntimeException("Address not found with id = " + id);
        }
        return address;
    }

    @Override
    public void updateAddress(Address address) {
        addressRepositoryImp.update(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepositoryImp.delete(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepositoryImp.readAll();
    }
}
