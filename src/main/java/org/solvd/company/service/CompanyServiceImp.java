package org.solvd.company.service;

import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.impl.CompanyRepositoryImp;
import org.solvd.company.service.interfcae.CompanyService;

import java.util.List;

public class CompanyServiceImp implements CompanyService {

    private CompanyRepositoryImp companyRepositoryImp;

    public CompanyServiceImp(CompanyRepositoryImp companyRepositoryImp) {
        this.companyRepositoryImp = companyRepositoryImp;
    }

    public void create(Company company) {
        companyRepositoryImp.create(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepositoryImp.get(id);
        if (company == null) throw new RuntimeException("Company is not Presented");
        return company;
    }

    @Override
    public void updateCompany(Company company) {
        companyRepositoryImp.update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepositoryImp.delete(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepositoryImp.readAll();
    }
}
