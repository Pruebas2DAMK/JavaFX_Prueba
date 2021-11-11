package singleton;

import java.sql.*;

public class ConexionBD {
    private static ConexionBD INSTANCE;
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    private ConexionBD() throws SQLException {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/severo_ad";
        con = DriverManager.getConnection(url,user,password);

    }

    public static ConexionBD getInstance() throws SQLException {
        if (INSTANCE == null){
            INSTANCE = new ConexionBD();
        }
        return INSTANCE;
    }


}
