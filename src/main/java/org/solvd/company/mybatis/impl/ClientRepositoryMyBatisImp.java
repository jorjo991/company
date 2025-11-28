package org.solvd.company.mybatis.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.client.Client;
import org.solvd.company.mybatis.ClientMapper;
import org.solvd.company.persistence.ClientsRepository;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryMyBatisImp implements ClientsRepository {

    private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Client client, Long company_id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            clientMapper.insert(client, company_id);
        }
    }

    @Override
    public void update(Client t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            clientMapper.update(t);
        }

    }

    @Override
    public Optional<Client> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            return clientMapper.selectById(id);
        }
    }

    @Override
    public void delete(Client client) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            clientMapper.delete(client.getId());
        }
    }

    @Override
    public List<Client> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            return clientMapper.selectAll();
        }
    }
}
