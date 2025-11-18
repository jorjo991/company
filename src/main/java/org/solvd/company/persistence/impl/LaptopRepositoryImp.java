package org.solvd.company.persistence.impl;

import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.persistence.DaoInteface.LaptopRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaptopRepositoryImp implements LaptopRepository {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Laptop laptop, Long employeeId) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO laptops (name, brand, color, employee_id, id) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, laptop.getName());
            ps.setString(2, laptop.getBrand());
            ps.setLong(4, employeeId);
            ps.setLong(5, laptop.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create laptop: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Laptop laptop) {
        String sql = "UPDATE laptops SET name=?, brand=?, color=? WHERE id=?";
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, laptop.getName());
            ps.setString(2, laptop.getBrand());
            ps.setString(3, laptop.getColor());
            ps.setLong(5, laptop.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update laptop: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Laptop get(Long id) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM laptops WHERE id=?";
        Laptop laptop = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                laptop = mapLaptop(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get laptop: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return laptop;
    }

    @Override
    public void delete(Laptop laptop) {
        String sql = "DELETE FROM laptops WHERE id=?";
        Connection connection=connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, laptop.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete laptop: " + e.getMessage(), e);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Laptop> readAll() {
        String sql = "SELECT * FROM laptops";
        List<Laptop> laptops = new ArrayList<>();
        Connection connection=connectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                laptops.add(mapLaptop(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read laptops: " + e.getMessage(), e);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }

        return laptops;
    }

    private Laptop mapLaptop(ResultSet rs) throws SQLException {
        Laptop laptop = new Laptop();
        laptop.setId(rs.getLong("id"));
        laptop.setName(rs.getString("name"));
        laptop.setBrand(rs.getString("brand"));
        laptop.setColor(rs.getString("color"));
        return laptop;
    }
}
