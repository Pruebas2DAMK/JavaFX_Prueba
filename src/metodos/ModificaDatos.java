package metodos;

import login.Login;
import singleton.DatabaseConnection;
import ventanaPrincipal.PrincipalController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModificaDatos {
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

    private static Login l;

    //****Añade un usuario****//
    public static Login anyadeUsuario(String usuario,String contrasenya,int salario, int admin) throws SQLException {
        l = new Login(usuario,contrasenya,salario,admin);
        String sql = "INSERT INTO login (username,password,salario,admin)VALUES (\'"+l.getUsername()+"\',\'"+l.getPassword()+"\',"+l.getSalario()+","+l.getAdmin()+")";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    return l;
}

    //****Modifica un usuario****//
    public static Login modificaUsuario(String usuario,String contrasenya,int salario, int admin) throws SQLException {
        //si no es admin, salario = salario anterior en el controlador
        l = new Login(usuario,contrasenya,salario,admin);
        String query = "UPDATE login set username = \'"+usuario+"\' , password = \'"+contrasenya+"\', salario = "+salario+", admin = "+admin+" WHERE id = "+l.getId();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        return l;
    }

    //****Metodo que elimina un usuario****//
    public static void eliminaUsuario(Login usuario) throws SQLException {
        String query = "DELETE FROM login WHERE username LIKE \'"+usuario.getUsername()+"\'";
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
    }
    //*****los usuarios convertidos en objetos*****//
    public static List<Login> datosUsuarios(int num) throws SQLException {
        List<Login> datos =new ArrayList<>();
        String sql;
        switch (num){
            case 1:
                sql = "SELECT * FROM login ORDER BY salario ASC"; //menor a mayor
                break;
            case 2:
                sql = "SELECT * FROM login ORDER BY salario DESC"; //mayor a menor
                break;
            default:
                sql = "SELECT * FROM login";
                break;
        }
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            datos.add( new Login(rs.getString("username")));

        }
        return datos;
    }
    //*****devolucion en cadena de los objetos Login*****//
    public static String getDatosUsuarios() throws SQLException {
        final String[] salida = {""};
        datosUsuarios(PrincipalController.num).forEach(e->{
            salida[0] +=e.toString();
        });
        return salida[0];
    }

    public static void main(String[] args) throws SQLException {
        //System.out.println(getDatosUsuarios());
    }
}
