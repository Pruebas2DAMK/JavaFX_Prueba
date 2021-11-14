package metodos;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import singleton.DatabaseConnection;

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
    public static void compruebaCamposLogin(String usuario, PasswordField contrasenya, Label label) throws IOException {
        String mensaje = "Contraseña Incorrecta. Por favor intentelo de nuevo";
        if (usuario.isEmpty() || contrasenya.getText().isEmpty()) {
            label.setText(mensaje);
            contrasenya.setText("");
        } else {
            //*****Conecta a la base de datos y comprueba*****//
            if (validateLogin(usuario,contrasenya.getText()) == 0) {
                label.setText(mensaje);
            } else {
                contrasenya.setText("");
                //abre la nueva ventana
                new ventanas().creaVentanaPrincipal();

            }
        }
    }
    //*******Metodo que comprueba si el usuario conectado tiene 'permisos'*******//
    public static boolean isSuperUsuario(String usuario) throws SQLException {
        boolean resultado = false;
        int salida;
        String query = "SELECT admin FROM login WHERE username =\'"+usuario+"\'";
        //TODO hacer metodo de esto para simplificar, se reutiliza
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //me devuelve el contador (0 - 1)
                salida = rs.getInt(1);
                if (salida == 0){
                    resultado = false;
                } else{
                    resultado = true;
                }
              
            }
        return resultado;
    }
    //****Si eres admin tienes acceso a unas cosas y si no,estas limitado.
   /*
   TODO primero en sucio en la propia clase y luego intentar abstraer lo maximo

    public static void camposUsables(Boolean permisos){

        if (!permisos){

        }

    }
     */




}
