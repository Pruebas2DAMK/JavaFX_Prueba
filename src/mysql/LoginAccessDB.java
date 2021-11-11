package mysql;

import com.mysql.cj.protocol.Resultset;
import modelo.Login;
import singleton.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginAccessDB {
    private static Connection con;

    static {
        try {
            con = ConexionBD.getInstance().getCon();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Login> getLogins() throws SQLException{
        String sql = "SELECT * from login";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Login> logins = new ArrayList<>();

        while (rs.next()){
            Login login = new Login();
            login.setId(rs.getInt("id"));
            login.setUsername(rs.getString("username"));
            login.setPassword(rs.getString(3));
            login.setCreated_at(rs.getTimestamp(4));
            logins.add(login);
        }
        return logins;
    }
    /*
    Comprueba si existe ese usuario con su clave correspondiente en la base de datos
     */
    public static String getLogin(String name, String password) throws SQLException {
        String salida="";
        String sql = "SELECT * FROM login where username LIKE \'"+name+"\' AND password LIKE \'"+ password+"\'";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        Login login = new Login();

           if(rs.next() == false){
                 salida="Contraseña o usuario incorrectos";
            }else{
               do {
                   login.setUsername(rs.getString("username"));
                   login.setPassword(rs.getString("password"));
                   salida = login.getUsername() + " - " + login.getPassword();
               }while (rs.next());
           }
        return salida;
    }
    public static void insertaUser(Login login) throws SQLException {
        String sql = "INSERT INTO login (username,password)VALUES (\'"+login.getUsername()+"\',\'"+login.getPassword()+"\')";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);

    }
    public static void borraUser(Login login) throws SQLException {
        String sql = "DELETE FROM login WHERE username LIKE \'"+login.getUsername()+"\'";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public static void actualizaUser(Login login) throws SQLException {
        String sql = "UPDATE login set username = \'"+login.getUsername()+"\' , password = \'"+login.getPassword()+"\' WHERE id = "+login.getId();
        //System.out.println(sql);
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public static String dameCosicas(int id) throws SQLException {
        String cadena="";
        String sql = "SELECT * FROM login WHERE id = "+id;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            cadena = rs.getInt("id")+" - "+rs.getString("username")+" - "+rs.getString("password")+" - "+rs.getDate("created_at");


        }
        return cadena;
    }

    public static void main(String[] args){
        try{
            System.out.println(dameCosicas(3));
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
