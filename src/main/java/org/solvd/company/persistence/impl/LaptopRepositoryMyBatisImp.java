package org.solvd.company.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.persistence.LaptopRepository;

import java.util.List;
import java.util.Optional;

public class LaptopRepositoryMyBatisImp implements LaptopRepository {

    private static final SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Laptop laptop, Long employeeId) {

        try (SqlSession session = sqlSessionFactory.openSession(true)) {

            LaptopRepository laptopMapper = session.getMapper(LaptopRepository.class);
            laptopMapper.create(laptop, employeeId);

        }

    }

    @Override
    public void update(Laptop laptop) {

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopRepository laptopMapper = session.getMapper(LaptopRepository.class);
            laptopMapper.update(laptop);
        }
    }

    @Override
    public Optional<Laptop> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopRepository laptopMapper = session.getMapper(LaptopRepository.class);
            return laptopMapper.get(id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopRepository laptopMapper = session.getMapper(LaptopRepository.class);
            laptopMapper.delete(id);
        }
    }

    @Override
    public List<Laptop> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopRepository laptopMapper = session.getMapper(LaptopRepository.class);
            return laptopMapper.readAll();

        }
    }
}
