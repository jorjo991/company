package org.solvd.company.mybatis;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientMapper {

    void insert(@Param("client") Client client, @Param("companyId") Long companyId);

    Optional<Client> selectById(Long id);

    List<Client> selectAll();

    void update(Client client);

    void delete(Long id);

}
