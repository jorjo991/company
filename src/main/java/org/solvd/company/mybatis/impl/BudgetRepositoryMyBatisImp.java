package org.solvd.company.mybatis.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.budget.Budget;
import org.solvd.company.mybatis.BudgetMapper;
import org.solvd.company.persistence.BudgetRepository;

import java.util.List;
import java.util.Optional;

public class BudgetRepositoryMyBatisImp implements BudgetRepository {

    private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Budget budget, Long companyId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetMapper budgetMapper = session.getMapper(BudgetMapper.class);
            budgetMapper.insert(budget, companyId);
        }
    }

    @Override
    public void update(Budget t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetMapper budgetMapper = session.getMapper(BudgetMapper.class);
            budgetMapper.update(t);
        }
    }

    @Override
    public Optional<Budget> get(Long id) {
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            BudgetMapper budgetMapper = session.getMapper(BudgetMapper.class);
            return budgetMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Budget budget) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetMapper budgetMapper = session.getMapper(BudgetMapper.class);
            budgetMapper.delete(budget.getId());
        }
    }

    @Override
    public List<Budget> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BudgetMapper budgetMapper = session.getMapper(BudgetMapper.class);
            return budgetMapper.selectAll();
        }
    }
}
