package org.solvd.company.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.company.Address;
import org.solvd.company.persistence.AddressRepository;

import java.util.List;
import java.util.Optional;

public class AddressRepositoryMyBatisImp implements AddressRepository {

    private final static SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Address address, Long officeId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressRepository addressMapper = session.getMapper(AddressRepository.class);
            addressMapper.create(address, officeId);
        }
    }

    @Override
    public void update(Address t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressRepository addressMapper = session.getMapper(AddressRepository.class);
            addressMapper.update(t);
        }
    }

    @Override
    public Optional<Address> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressRepository addressMapper = session.getMapper(AddressRepository.class);
            return addressMapper.get(id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressRepository addressMapper = session.getMapper(AddressRepository.class);
            addressMapper.delete(id);
        }
    }

    @Override
    public List<Address> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressRepository addressMapper = session.getMapper(AddressRepository.class);
            return addressMapper.readAll();
        }
    }
}
