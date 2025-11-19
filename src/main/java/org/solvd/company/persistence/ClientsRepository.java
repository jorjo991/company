package org.solvd.company.persistence;

import org.solvd.company.domain.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository {

    void create(Client client, Long company_id);

    void update(Client t);

    Optional<Client> get(Long id);

    void delete(Client client);

    List<Client> readAll();

}
