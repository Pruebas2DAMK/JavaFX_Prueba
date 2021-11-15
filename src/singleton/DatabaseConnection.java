package singleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection INSTANCE;
    private Connection con;
    private Properties p;

    public Connection getConnection() {
        return con;
    }

    public DatabaseConnection() throws SQLException, IOException, ClassNotFoundException {
        p = new Properties();
        p.load(new BufferedReader(new FileReader("./src/connection.properties")));
        String user = p.getProperty("user");
        String password = p.getProperty("password");
        String url = p.getProperty("url");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public static DatabaseConnection getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (INSTANCE == null){
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }

}
