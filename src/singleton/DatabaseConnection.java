package singleton;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection INSTANCE;
    private final Connection con;

    public Connection getConnection() {
        return con;
    }

    private DatabaseConnection() throws SQLException {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/severo_ad";
        con = DriverManager.getConnection(url,user,password);

    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (INSTANCE == null){
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }


}
