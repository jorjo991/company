package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.ClientsRepository;
import org.solvd.company.service.ClientService;

import java.util.List;

public class ClientServiceImp implements ClientService {

    private final ClientsRepository clientRepository;

    public ClientServiceImp(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client, Long companyId) {
        clientRepository.create(client, companyId);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.get(id).orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.readAll();
    }
}
