package org.solvd.company.persistence.impl;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.persistence.BudgetRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BudgetRepositoryImp implements BudgetRepository {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Budget budget, Long companyId) {
        Connection connection = connectionPool.getConnection();
        String createStatement = "Insert into budgets (total_amount,spend_amount,description,company_id) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, budget.getTotalAmount());
            preparedStatement.setDouble(2, budget.getSpent());
            preparedStatement.setString(3, budget.getBelongs());
            preparedStatement.setLong(4, companyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Budget budget) {
        Connection connection = connectionPool.getConnection();

        String updateCommand = "update budgets  set total_amount =? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {
            preparedStatement.setLong(2, budget.getId());
            preparedStatement.setDouble(1, budget.getTotalAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Budget> get(Long id) {
        Connection connection = connectionPool.getConnection();
        String getCommand = "select id,total_amount,spend_amount,description from budgets where id=?";
        Budget budget = new Budget();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCommand)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                budget = getBudget(resultSet.getLong("id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getDouble("spend_amount"), resultSet.getString("description"));
            }
            return Optional.of(budget);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Budget budget) {
        Connection connection = connectionPool.getConnection();
        String deleteString = "Delete from budgets where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteString)) {
            preparedStatement.setLong(1, budget.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Budget> readAll() {
        Connection connection = connectionPool.getConnection();
        List<Budget> budgets = new ArrayList<>();
        String getCommand = "select  id,total_amount,spend_amount,description from budgets";
        Budget budget = new Budget();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                budget = getBudget(resultSet.getLong("id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getDouble("spend_amount"), resultSet.getString("description"));
                budgets.add(budget);
            }
            return budgets;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static Budget getBudget(Long budgetID, Double totalAmount, Double spent, String belongs) {
        return new Budget(budgetID, totalAmount, spent, belongs);
    }
}
