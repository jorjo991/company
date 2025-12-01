package org.solvd.company.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.budget.Budget;
import org.solvd.company.persistence.BudgetRepository;

import java.util.List;
import java.util.Optional;

public class BudgetRepositoryMyBatisImp implements BudgetRepository {

    private final static SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Budget budget, Long companyId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetRepository budgetMapper = session.getMapper(BudgetRepository.class);
            budgetMapper.create(budget, companyId);
        }
    }

    @Override
    public void update(Budget t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetRepository budgetMapper = session.getMapper(BudgetRepository.class);
            budgetMapper.update(t);
        }
    }

    @Override
    public Optional<Budget> get(Long id) {
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            BudgetRepository budgetMapper = session.getMapper(BudgetRepository.class);
            return budgetMapper.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetRepository budgetMapper = session.getMapper(BudgetRepository.class);
            budgetMapper.delete(id);
        }
    }

    @Override
    public List<Budget> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetRepository budgetMapper = session.getMapper(BudgetRepository.class);
            return budgetMapper.readAll();
        }
    }
}
