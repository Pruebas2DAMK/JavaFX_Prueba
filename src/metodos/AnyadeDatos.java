package metodos;

import login.Login;
import singleton.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AnyadeDatos {
    private static Connection con;

    static {
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ;
    private static Login l;

    //****Añade un usuario****//
    public static Login anyadeUsuario(String usuario,String contrasenya,int salario, int admin) throws SQLException {
        l = new Login(usuario,contrasenya,salario,admin);
        String sql = "INSERT INTO login (username,password,salario,admin)VALUES (\'"+l.getUsername()+"\',\'"+l.getPassword()+"\',"+l.getSalario()+","+l.getAdmin()+")";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    return l;
}

}
