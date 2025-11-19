package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.persistence.impl.CompanyRepositoryImp;
import org.solvd.company.service.CompanyService;

import java.util.List;

public class CompanyServiceImp implements CompanyService {

    private final CompanyRepositoryImp companyRepositoryImp;

    public CompanyServiceImp(CompanyRepositoryImp companyRepositoryImp) {
        this.companyRepositoryImp = companyRepositoryImp;
    }

    public void create(Company company) {
        ClientServiceImp clientServiceImp = new ClientServiceImp(new ClientsRepositoryImp());
        for (Client client : company.getClients()) {
            clientServiceImp.create(client, company.getId());
        }
        companyRepositoryImp.create(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepositoryImp.get(id).orElseThrow(() -> new RuntimeException("Company not found with id " + id));
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
