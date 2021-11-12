package metodos;

import javafx.scene.control.PasswordField;
import singleton.DatabaseConnection;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompruebaDatos {
    private static Connection con;

    //*****Verifica la conexion a la base de datos*****//
    public static int validateLogin(String usuario, String contrasenya) {
        int valor = 0;
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String verificaLogin = "SELECT count(1) FROM login WHERE username =\'" +usuario  + "\' AND password = \'" +contrasenya + "\'";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(verificaLogin);
            while (rs.next()) {
                //me devuelve el contador (0 - 1)
                valor = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return valor;
    }

    //******Comprueba que los campos no estas vacios******//
    public static void compruebaCamposLogin(String usuario, String contrasenya, Label label){
        String mensaje = "Contraseña Incorrecta. Por favor intentelo de nuevo";
        if (usuario.isEmpty() || contrasenya.isEmpty()) {
            label.setText(mensaje);
            contrasenya = "";
        } else {
            //*****Conecta a la base de datos y comprueba*****//
            if (validateLogin(usuario,contrasenya) == 0) {
                label.setText(mensaje);
            } else {
                label.setText("Conectado con exito");
            }
        }
    }

}
