package airlinemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Conn manages JDBC connectivity to the MySQL database.
 */
public class Conn {
    public Connection c;
    public Statement s;

    // Database configurations
    private static final String DB_URL = System.getProperty("db.url", 
        System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/airlinemanagementsystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"));
    private static final String DB_USER = System.getProperty("db.user", 
        System.getenv().getOrDefault("DB_USER", "root"));
    private static final String DB_PASS = System.getProperty("db.password", 
        System.getenv().getOrDefault("DB_PASSWORD", "NewPassword@123")); // Set your MySQL password here

    public Conn() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            s = c.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Make sure mysql-connector-j.jar is added to the classpath.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}