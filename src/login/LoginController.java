package login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import singleton.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {


    //********Objetos********//
    private static Connection con;

    @FXML
    private Button btnEntrar;
    @FXML
    private Label laAvisos;
    @FXML
    private PasswordField pfContrasenya;
    @FXML
    private TextField tfUsuario;
    @FXML
    private Button btnSalir;

    //********Acciones********//

    //*******Salir*******//
    public void btnSalirOnAction(ActionEvent actionEvent) {
        //Stage es la clase top de JavaFX, capturo la ventana y la cierro
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    public void btnEntrarOnAction(ActionEvent actionEvent) {
        String mensaje = "Contraseña Incorrecta. Por favor intentelo de nuevo";
        if (tfUsuario.getText().isEmpty() || pfContrasenya.getText().isEmpty()) {
            //si los campos estan vacios, limpia contraseña y saca un mensaje de aviso
            laAvisos.setText(mensaje);
            pfContrasenya.clear();
        } else {
            //*****Conecta a la base de datos y comprueba*****//
            if (validateLogin() == 0) {
                laAvisos.setText(mensaje);
            } else {
                laAvisos.setText("Conectado con exito");
            }
        }
    }

    //*****Verifica la conexion a la base de datos*****//
    private int validateLogin() {
        int valor = 0;
        try {
            con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String verificaLogin = "SELECT count(1) FROM login WHERE username =\'" + tfUsuario.getText() + "\' AND password = \'" + pfContrasenya.getText() + "\'";
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

    //*****'Controla' si se ha pulsado la tecla mayuscula (va un poco de lado)*****//
    public void btnControlaMayus(KeyEvent keyEvent) {
        if (keyEvent.getText() == keyEvent.getText().toUpperCase()){ 
            laAvisos.setText("Bloq Mayus Activado");
        } else{
            laAvisos.setText("");
        }

    }


}