package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {



//********Objetos********//

    @FXML
    private Button btnEntrar;
    @FXML
    private Label laNotContrasenya;
    @FXML
    private PasswordField pfContrasenya;
    @FXML
    private TextField tfUsuario;

//********Acciones********//

    public void btnEntrarOnAction(ActionEvent actionEvent) {
        if (tfUsuario.getText().isEmpty() || pfContrasenya.getText().isEmpty()){
            //si los campos estan vacios, limpia contraseña y saca un mensaje de aviso
            laNotContrasenya.setText("Contraseña Incorrecta. Por favor intentelo de nuevo");
            pfContrasenya.clear();
        } else{
            //TODO loguearse
        }
    }

}
