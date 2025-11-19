package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.client.Client;
import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.CompanyRepository;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.ClientService;
import org.solvd.company.service.CompanyService;

import java.util.List;

public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ClientService clientService;

    public CompanyServiceImp(CompanyRepository companyRepository, ClientService clientService) {
        this.companyRepository = companyRepository;
        this.clientService = clientService;
    }

    public void create(Company company) {
        companyRepository.create(company);
        for (Client client : company.getClients()) {
            clientService.create(client, company.getId());
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
