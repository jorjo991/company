package org.solvd.company.persistence.impl;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.persistence.DaoInteface.BudgetRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetRepositoryImp implements BudgetRepository {

    private final ConnectionPool connectionPool=ConnectionPool.getInstance();

    @Override
    public void create(Budget budget, Long companyId) {
        Connection connection=connectionPool.getConnection();
        String createStatement = "Insert into budgets (total_amount,spend_amount,description,company_id,id) values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createStatement)) {
            preparedStatement.setDouble(1, budget.getTotalAmount());
            preparedStatement.setDouble(2, budget.getSpent());
            preparedStatement.setString(3, budget.getBelongs());
            preparedStatement.setLong(4, companyId);
            preparedStatement.setLong(5, budget.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Budget budget) {
        Connection connection=connectionPool.getConnection();

        String updateCommand = "update budgets  set total_amount =? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {
            preparedStatement.setLong(2, budget.getId());
            preparedStatement.setDouble(1, budget.getTotalAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Budget get(Long id) {
        Connection connection=connectionPool.getConnection();
        String getCommand = "select * from budgets where id=?";
        Budget budget = new Budget();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCommand)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                budget = getBudget(resultSet.getLong("id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getDouble("spend_amount"), resultSet.getString("description"));
            }
            return budget;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Budget budget) {
        Connection connection=connectionPool.getConnection();
        String deleteString = "Delete from budgets where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteString)) {
            preparedStatement.setLong(1, budget.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Budget> readAll() {
        Connection connection=connectionPool.getConnection();
        List<Budget> budgets = new ArrayList<>();
        String getCommand = "select * from budgets";
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
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static Budget getBudget(Long budgetID, Double totalAmount, Double spent, String belongs) {
        return new Budget(budgetID, totalAmount, spent, belongs);
    }
}
