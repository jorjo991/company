package org.solvd.company.persistence.DaoInteface;

import org.solvd.company.domain.client.Client;

import java.util.List;

public interface ClientsRepository {

    void  create(Client client, Long company_id);
    void  update(Client t);
    Client  get(Long id);
    void  delete(Client client);
    List<Client> readAll();

}
