package org.solvd.company.mybatis;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.budget.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetMapper {

    void insert(@Param("budget") Budget budget, @Param("companyId") Long companyId);

    Optional<Budget> selectById(Long id);

    List<Budget> selectAll();

    void update(Budget budget);

    void delete(Long id);
}
