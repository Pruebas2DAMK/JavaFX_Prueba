package login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import metodos.CompruebaDatos;
import singleton.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static metodos.CompruebaDatos.*;

public class LoginController {


    //********Objetos********//


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
    //****Controla los campos y devuelve un label con mensaje
    public void btnEntrarOnAction(ActionEvent actionEvent) {
        compruebaCamposLogin(tfUsuario.getText(), pfContrasenya.getText(), laAvisos);
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