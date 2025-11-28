package org.solvd.company.mybatis.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.company.Address;
import org.solvd.company.mybatis.AddressMapper;
import org.solvd.company.persistence.AddressRepository;

import java.util.List;
import java.util.Optional;

public class AddressRepositoryMyBatisImp implements AddressRepository {

    private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Address address, Long officeId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper addressMapper = session.getMapper(AddressMapper.class);
            addressMapper.insert(address, officeId);
        }
    }

    @Override
    public void update(Address t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper addressMapper = session.getMapper(AddressMapper.class);
            addressMapper.update(t);
        }
    }

    @Override
    public Optional<Address> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper addressMapper = session.getMapper(AddressMapper.class);
            return addressMapper.selectById(id);
        }
    }

    @Override
    public void delete(Address address) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper addressMapper = session.getMapper(AddressMapper.class);
            addressMapper.delete(address.getId());
        }
    }

    @Override
    public List<Address> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AddressMapper addressMapper = session.getMapper(AddressMapper.class);
            return addressMapper.selectAll();
        }
    }
}
