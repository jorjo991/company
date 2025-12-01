package org.solvd.company.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.ClientsRepository;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryMyBatisImp implements ClientsRepository {

    private final static SqlSessionFactory sqlSessionFactory = MybatisUtil.getSession();

    @Override
    public void create(Client client, Long company_id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientsRepository clientMapper = session.getMapper(ClientsRepository.class);
            clientMapper.create(client, company_id);
        }
    }

    @Override
    public void update(Client t) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientsRepository clientMapper = session.getMapper(ClientsRepository.class);
            clientMapper.update(t);
        }

    }

    @Override
    public Optional<Client> get(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientsRepository clientMapper = session.getMapper(ClientsRepository.class);
            return clientMapper.get(id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientsRepository clientMapper = session.getMapper(ClientsRepository.class);
            clientMapper.delete(id);
        }
    }

    @Override
    public List<Client> readAll() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ClientsRepository clientMapper = session.getMapper(ClientsRepository.class);
            return clientMapper.readAll();
        }
    }
}
