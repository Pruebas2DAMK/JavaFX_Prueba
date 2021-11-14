package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static metodos.CompruebaDatos.compruebaCamposLogin;

public class LoginController {


    //********Objetos********//
    static Login login;

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
    public void btnEntrarOnAction(ActionEvent actionEvent) throws IOException {
        login = new Login(tfUsuario.getText());
        compruebaCamposLogin(tfUsuario.getText(), pfContrasenya, laAvisos);

    }
    //*****'Controla' si se ha pulsado la tecla mayuscula (va un poco de lado)*****//
    public void btnControlaMayus(KeyEvent keyEvent) {
        if (keyEvent.getText() == keyEvent.getText().toUpperCase()){
            laAvisos.setText("Bloq Mayus Activado");
        } else{
            laAvisos.setText("");
        }

    }

    //***Buscando medios para recuperar datos***//
    public static String getUserLogin() {
        return login.getUsername();
    }
}