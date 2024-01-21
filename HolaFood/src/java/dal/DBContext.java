package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    protected Connection connection;
    private final String dbName = "food_test5";

    public DBContext() {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName="+ dbName;
            String username = "sa";
            String password = "12345678";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(new DBContext().connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
