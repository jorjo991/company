package org.solvd.company.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.CompanyRepository;

import java.util.List;
import java.util.Optional;

public class CompanyRepositoryMyBatisImp implements CompanyRepository {

    private final static SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Company company) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyRepository companyMapper = session.getMapper(CompanyRepository.class);
            companyMapper.create(company);
        }
    }

    @Override
    public void update(Company company) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyRepository companyMapper = session.getMapper(CompanyRepository.class);
            companyMapper.update(company);
        }
    }

    @Override
    public Optional<Company> get(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyRepository companyMapper = session.getMapper(CompanyRepository.class);
            return companyMapper.get(id);
        }
    }

    @Override
    public void delete(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyRepository companyMapper = session.getMapper(CompanyRepository.class);
            companyMapper.delete(id);
        }
    }

    @Override
    public List<Company> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyRepository companyMapper = session.getMapper(CompanyRepository.class);
            return companyMapper.readAll();
        }
    }
}
