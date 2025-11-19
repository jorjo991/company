package org.solvd.company.persistence.impl;

import org.solvd.company.domain.task.Task;
import org.solvd.company.persistence.TaskRepository;

import java.lang.classfile.Opcode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImp implements TaskRepository {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Task task, Long projectId) {

        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO tasks (name, start_time, end_time, project_id, id) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, task.getName());
            ps.setDate(2, Date.valueOf(task.getStartTime()));
            ps.setDate(3, Date.valueOf(task.getEndTime()));
            ps.setLong(4, projectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create task: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Task task) {

        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE tasks SET name=?, start_time=?, end_time=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, task.getName());
            ps.setDate(2, Date.valueOf(task.getStartTime()));
            ps.setDate(3, Date.valueOf(task.getEndTime()));
            ps.setLong(4, task.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update task: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Task> get(Long id) {
        String sql = "SELECT id,name,start_time,end_time FROM tasks WHERE id=?";
        Task task = new Task();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                task = mapTask(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get task: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return Optional.of(task);
    }

    @Override
    public void delete(Task task) {
        String sql = "DELETE FROM tasks WHERE id=?";
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, task.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete task: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Task> readAll() {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id,name,start_time,end_time FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tasks.add(mapTask(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read tasks: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return tasks;
    }

    private Task mapTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setName(rs.getString("name"));
        task.setStartTime(rs.getDate("start_time").toLocalDate());
        task.setEndTime(rs.getDate("end_time").toLocalDate());
        return task;
    }
}
