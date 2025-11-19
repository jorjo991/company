package org.solvd.company.persistence.impl;

import org.solvd.company.domain.company.Address;
import org.solvd.company.persistence.AddressRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryImp implements AddressRepository {

    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Address address, Long officeId) {
        Connection connection = pool.getConnection();
        String createStatement =
                "INSERT INTO addresses (city, street, number, office_id) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(createStatement, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getNumber());
            ps.setLong(4, officeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create address: " + e.getMessage(), e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Address address) {
        String update = "UPDATE addresses SET city=?, street=?, number=? WHERE id=?";
        Connection connection = pool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getNumber());
            ps.setLong(5, address.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update address: " + e.getMessage(), e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Address> get(Long id) {
        Connection connection = pool.getConnection();
        String get = "SELECT city, street, number, office_id, id FROM addresses WHERE id=?";
        Address address = null;

        try (PreparedStatement ps = connection.prepareStatement(get)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                address = mapAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get address: " + e.getMessage(), e);
        } finally {
            pool.releaseConnection(connection);
        }

        return Optional.of(address);
    }

    @Override
    public void delete(Address address) {
        Connection connection = pool.getConnection();

        String delete = "DELETE FROM addresses WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setLong(1, address.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete address: " + e.getMessage(), e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Address> readAll() {
        Connection connection = pool.getConnection();

        String getAll = "SELECT city, street, number,id  FROM addresses";
        List<Address> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(getAll)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapAddress(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read addresses: " + e.getMessage(), e);
        } finally {
            pool.releaseConnection(connection);
        }

        return list;
    }

    private Address mapAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong("id"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setNumber(rs.getString("number"));
        return address;
    }
}
