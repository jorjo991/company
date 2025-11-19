package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.service.ClientService;

import java.util.List;

public class ClientServiceImp implements ClientService {

    private final ClientsRepositoryImp clientRepositoryImp;

    public ClientServiceImp(ClientsRepositoryImp clientRepositoryImp) {
        this.clientRepositoryImp = clientRepositoryImp;
    }

    @Override
    public void create(Client client, Long companyId) {
        clientRepositoryImp.create(client, companyId);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepositoryImp.get(id).orElseThrow(() -> new RuntimeException("Client not found with id " + id));
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
