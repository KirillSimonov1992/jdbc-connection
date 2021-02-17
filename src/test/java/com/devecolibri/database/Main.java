package com.devecolibri.database;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";

    public static void main(String[] args) {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Failed download jdbc driver!");
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES('batch1', 'desc');");
            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES('batch2', 'desc');");
            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES('batch3', 'desc');");
            statement.executeBatch();

            statement.clearBatch();
            System.out.println(statement.isClosed());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
