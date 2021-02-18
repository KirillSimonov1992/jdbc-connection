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
        DBWorker worker = new DBWorker();
        String query = "SELECT * FROM users";
        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
