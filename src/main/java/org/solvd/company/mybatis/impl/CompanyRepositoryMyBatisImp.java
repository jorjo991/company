package org.solvd.company.mybatis.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.company.Company;
import org.solvd.company.mybatis.CompanyMapper;
import org.solvd.company.persistence.CompanyRepository;

import java.util.List;
import java.util.Optional;

public class CompanyRepositoryMyBatisImp implements CompanyRepository {

    private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Company company) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            companyMapper.insert(company);
        }
    }

    @Override
    public void update(Company company) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            companyMapper.update(company);
        }
    }

    @Override
    public Optional<Company> get(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            return companyMapper.selectById(id);
        }
    }

    @Override
    public void delete(Company t) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            companyMapper.delete(t.getId());
        }
    }

    @Override
    public List<Company> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            return companyMapper.selectAll();
        }
    }
}
