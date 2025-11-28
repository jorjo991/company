package org.solvd.company.mybatis;

import org.solvd.company.domain.company.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyMapper {

    void insert(Company company);

    Optional<Company> selectById(Long id);

    List<Company> selectAll();

    void update(Company Company);

    void delete(Long id);
}
