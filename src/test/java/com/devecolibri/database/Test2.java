package com.devecolibri.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Test2 {

    private static final String INSERT_ROW = "INSERT INTO dish VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL = "SELECT * FROM dish;";
    private static final String DELETE_SPECIFIED = "DELETE FROM dish where id = ?;";

    public static void main(String[] args) throws SQLException {
        DBWorker worker = new DBWorker();
        PreparedStatement preparedStatement = null;
        try {
            // Add row
            preparedStatement = worker.getConnection()
                    .prepareStatement(INSERT_ROW);
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "title");
            preparedStatement.setString(3, "description");
            preparedStatement.setFloat(4, 0.2f);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setBlob(7, new FileInputStream("redDot.png"));

            preparedStatement.execute();

            // Get row
            preparedStatement = worker.getConnection()
                                      .prepareStatement(SELECT_ALL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                float rating = resultSet.getFloat("rating");
                boolean published = resultSet.getBoolean("published");
                Date dateCreated = resultSet.getDate("created");
                byte[] icon = resultSet.getBytes("icon");

                System.out.println("id: " + id +"; title: " + title +
                        "; description: " + description + "; etc.");
            }

            // Delete row at specified id
            preparedStatement = worker.getConnection()
                                      .prepareStatement(DELETE_SPECIFIED);
            preparedStatement.setInt(1, 2);

            System.out.println("deleted count row: " + preparedStatement.executeUpdate());

        } catch (FileNotFoundException e) {
            System.out.println("Don't load picture for dish. By cause " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Don't get prepared statement. By cause " + e.getMessage());
        } finally {
            preparedStatement.close();
            worker.closeConnection();
        }
    }
}
