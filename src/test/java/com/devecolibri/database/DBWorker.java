package com.devecolibri.database;

import java.sql.*;

public class DBWorker {

    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "bestuser";
    private final String PASSWORD = "bestuser";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed download jdbc driver!");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
