package org.solvd.company.service.interfcae;

import org.solvd.company.domain.client.Client;

import java.util.List;

public interface ClientService {

    void create(Client client, Long companyId);

    Client getClientById(Long id);

    void updateClient(Client client);

    void deleteClient(Client client);

    List<Client> getAllClients();
}
