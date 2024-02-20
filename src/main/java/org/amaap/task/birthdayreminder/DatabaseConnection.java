package org.amaap.task.birthdayreminder;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static boolean isConnected = false;

    static Connection getDbConnection() throws SQLException {
        Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amaap", "root", "root");
            isConnected = true;
        }
        catch (Exception e){
           throw new SQLException("Error while connecting");
        }
        return con;

    }
}
