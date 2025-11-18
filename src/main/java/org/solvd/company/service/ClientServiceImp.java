package org.solvd.company.service;

import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.DaoInteface.ClientsRepository;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.interfcae.ClientService;

import java.util.List;

public class ClientServiceImp implements ClientService {

    private ClientsRepositoryImp clientRepositoryImp;

    public ClientServiceImp(ClientsRepositoryImp clientRepositoryImp) {
        this.clientRepositoryImp = clientRepositoryImp;
    }

    @Override
    public void create(Client client, Long companyId) {
        clientRepositoryImp.create(client, companyId);
    }

    @Override
    public Client getClientById(Long id) {
        Client client = clientRepositoryImp.get(id);
        if (client == null) {
            throw new RuntimeException("Client not found with id = " + id);
        }
        return client;
    }

    @Override
    public void updateClient(Client client) {
        clientRepositoryImp.update(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepositoryImp.delete(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryImp.readAll();
    }
}
