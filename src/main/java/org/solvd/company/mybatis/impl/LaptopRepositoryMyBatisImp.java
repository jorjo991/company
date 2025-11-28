package org.solvd.company.mybatis.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.mybatis.LaptopMapper;
import org.solvd.company.persistence.LaptopRepository;

import java.util.List;
import java.util.Optional;

public class LaptopRepositoryMyBatisImp implements LaptopRepository {

    private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Laptop laptop, Long employeeId) {

        try (SqlSession session = sqlSessionFactory.openSession(true)) {

            LaptopMapper laptopMapper = session.getMapper(LaptopMapper.class);
            laptopMapper.insert(laptop, employeeId);

        }

    }

    @Override
    public void update(Laptop laptop) {

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopMapper laptopMapper = session.getMapper(LaptopMapper.class);
            laptopMapper.update(laptop);
        }
    }

    @Override
    public Optional<Laptop> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopMapper laptopMapper = session.getMapper(LaptopMapper.class);
            return laptopMapper.selectById(id);
        }
    }

    @Override
    public void delete(Laptop laptop) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopMapper laptopMapper = session.getMapper(LaptopMapper.class);
            laptopMapper.delete(laptop.getId());
        }
    }

    @Override
    public List<Laptop> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            LaptopMapper laptopMapper = session.getMapper(LaptopMapper.class);
            return laptopMapper.selectAll();

        }
    }
}
