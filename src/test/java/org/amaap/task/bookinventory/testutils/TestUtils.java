package org.amaap.task.bookinventory.testutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class TestUtils {
    public static void cleanDatabase(String url, String username, String password) throws SQLException {
        try (Connection connection = getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM book_inventory");
        } catch (SQLException e) {
            // Handle or propagate the exception
            throw e;
        }
    }
}
