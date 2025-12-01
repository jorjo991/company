package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.company.Address;
import org.solvd.company.persistence.impl.AddressRepositoryMyBatisImp;
import org.solvd.company.persistence.AddressRepository;
import org.solvd.company.service.AddressService;

import java.util.List;

public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImp() {
        this.addressRepository = new AddressRepositoryMyBatisImp();
    }

    @Override
    public void create(Address address, Long id) {
        addressRepository.create(address, id);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.get(id).orElseThrow(() -> new RuntimeException("Laptop not found with id " + id));
    }

    @Override
    public void updateAddress(Address address) {
        addressRepository.update(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address.getId());
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.readAll();
    }
}
