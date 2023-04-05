package objects;

import java.sql.*;

public class SetConnection {
    private static String url = "jdbc:mysql://localhost/tryouts";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "";

    private static Connection con;

    private static String urlString;

    public static Connection getConnection() {

        try {

            Class.forName(driverName);

            try {

                con = DriverManager.getConnection(url, username, password);

            } catch (SQLException ex) {

                // log an exception. fro example:

                System.out.println("Failed to create the database connection.");

            }

        } catch (ClassNotFoundException ex) {

            System.out.println("Driver not found.");

        }

        return con;

    }
}
