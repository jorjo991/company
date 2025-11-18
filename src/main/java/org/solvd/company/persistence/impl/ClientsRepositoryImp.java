package org.solvd.company.persistence.impl;

import org.solvd.company.domain.client.Client;
import org.solvd.company.persistence.DaoInteface.ClientsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepositoryImp implements ClientsRepository {

   private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Client client, Long companyId) {
        Connection connection=connectionPool.getConnection();
        String create =
                "INSERT INTO clients (id, name, surname, email, birthday, active, company_id, age) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(create)) {
            ps.setLong(1, client.getId());
            ps.setString(2, client.getName());
            ps.setString(3, client.getSurname());
            ps.setString(4, client.getEmail());
            ps.setDate(5, client.getBirthday() != null ? Date.valueOf(client.getBirthday()) : null);
            ps.setBoolean(6, client.getActive());
            ps.setLong(7, companyId);
            ps.setInt(8, client.getAge());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create client: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Client client) {
        Connection connection=connectionPool.getConnection();
        String update =
                "UPDATE clients SET name=?, surname=?, email=?, birthday=?, active=?, age=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getEmail());
            ps.setDate(4, client.getBirthday() != null ? Date.valueOf(client.getBirthday()) : null);
            ps.setBoolean(5, client.getActive());
            ps.setInt(6, client.getAge());
            ps.setLong(7, client.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update client: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Client get(Long id) {
        Connection connection=connectionPool.getConnection();
        String get = "SELECT * FROM clients WHERE id=?";
        Client client = null;

        try (PreparedStatement ps = connection.prepareStatement(get)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = mapClient(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get client: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return client;
    }

    @Override
    public void delete(Client client) {
        Connection connection=connectionPool.getConnection();
        String delete = "DELETE FROM clients WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setLong(1, client.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete client: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Client> readAll() {
        Connection connection=connectionPool.getConnection();
        String getAll = "SELECT * FROM clients";
        List<Client> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(getAll)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapClient(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read clients: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return list;
    }

    private Client mapClient(ResultSet rs) throws SQLException {
        Client client = new Client();

        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setSurname(rs.getString("surname"));
        client.setEmail(rs.getString("email"));

        Date birthday = rs.getDate("birthday");
        client.setBirthday(birthday != null ? birthday.toLocalDate() : null);

        client.setActive(rs.getBoolean("active"));
        client.setAge(rs.getInt("age"));

        return client;
    }
}
