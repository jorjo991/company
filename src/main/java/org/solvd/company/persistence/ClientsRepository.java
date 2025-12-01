package org.solvd.company.persistence;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository {

    void create(@Param("client") Client client, @Param("companyId") Long company_id);

    void update(Client t);

    Optional<Client> get(Long id);

    void delete(Long id);

    List<Client> readAll();

}
