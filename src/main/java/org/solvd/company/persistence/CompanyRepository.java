package org.solvd.company.persistence;

import org.solvd.company.domain.company.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    void create(Company company);

    void update(Company company);

    Optional<Company> get(Long id);

    void delete(Company t);

    List<Company> readAll();

}
