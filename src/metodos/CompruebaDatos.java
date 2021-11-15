package metodos;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.Login;
import singleton.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompruebaDatos {
    private static Connection con;

    //*****Verifica la conexion a la base de datos*****//
    public static int validateLogin(String usuario, String contrasenya) throws SQLException, IOException, ClassNotFoundException {
        int valor = 0;
        conectaBD();
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

    //****Comprueba si existe****//
    public static Boolean isUsuarioExistente(Login usuario){
        int valor=0;
        Boolean resultado=false;
        String query = "SELECT count(1) FROM login WHERE username =\'" +usuario.getUsername()  + "\'";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                valor = rs.getInt(1);

            }
           if (valor == 1){
               resultado=true;
           }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return resultado;
    }


    //****Conecta para comprobar****//
    private static void conectaBD() {
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    //******Comprueba que los campos no estas vacios******//
    public static void compruebaCamposLogin(String usuario, PasswordField contrasenya, Label label) throws IOException, SQLException, ClassNotFoundException {
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
                new ventana().creaVentanaPrincipal();

            }
        }
    }
    //*******Metodo que comprueba si el usuario conectado tiene 'permisos'*******//
    public static boolean isSuperUsuario(String usuario) throws SQLException {
        boolean resultado = false;
        int salida;
        String query = "SELECT admin FROM login WHERE username =\'"+usuario+"\'";
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

    //****Metodo que prohibe la introducion de caracteres alfabeticos****//Extraido de java2s.com//
    public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            private ObservableValue<? extends String> observable;
            private String oldValue;
            private String newValue;

            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                this.observable = observable;
                this.oldValue = oldValue;
                this.newValue = newValue;
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}
