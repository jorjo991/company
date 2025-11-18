package org.solvd.company.service.interfcae;

import org.solvd.company.domain.company.Company;

import java.util.List;

public interface CompanyService {

    void create(Company company);

    Company getCompanyById(Long id);

    void updateCompany(Company company);

    void deleteCompany(Company company);

    List<Company> getAllCompanies();

}
