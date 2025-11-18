package org.solvd.company.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final BlockingQueue<Connection> connectionPool;
    private final String url = "jdbc:postgresql://localhost:5432/company";
    private final String user = "postgres";
    private final String password = "evashechema123";

    private final int POOL_SIZE = 10;

    private ConnectionPool() {
        connectionPool = new ArrayBlockingQueue<>(POOL_SIZE);
        init();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private void init() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionPool.add(connection);
            }
            System.out.println("Connection Pool initialized with " + POOL_SIZE + " connections");
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    public Connection getConnection() {
        try {
            return connectionPool.take(); // waits if needed
        } catch (InterruptedException e) {
            throw new RuntimeException("Could not get connection from pool", e);
        }

    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.offer(connection);
        }
    }

    public void shutdown() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }
}
