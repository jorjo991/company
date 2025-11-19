package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.CompanyRepository;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.CompanyService;

import java.util.List;

public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void create(Company company) {
        companyRepository.create(company);
        ClientServiceImp clientServiceImp = new ClientServiceImp(new ClientsRepositoryImp());
        for (Client client : company.getClients()) {
            clientServiceImp.create(client, company.getId());
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.get(id).orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.readAll();
    }
}
