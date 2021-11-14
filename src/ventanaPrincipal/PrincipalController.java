package ventanaPrincipal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

import static login.LoginController.getUserLogin;
import static metodos.CompruebaDatos.isSuperUsuario;

public class PrincipalController {

    @FXML
    private Button BtnEliminar;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnDesconectar;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnVerTodo;
    @FXML
    private Label laAvisos;
    @FXML
    private Label laSaludoUsuario;
    @FXML
    private PasswordField pfContrasenya;
    @FXML
    private TextArea taResultado;
    @FXML
    private TextField tfUsuario;
    @FXML
    private ComboBox cbOrdenSalario;

    //****Salir de la ventana principal y volver al login****//
    @FXML
    void btnDesconectarOnAction(ActionEvent event) {
        Stage stage = (Stage) btnDesconectar.getScene().getWindow();
        stage.close();

    }

    //****Devuelve una funcion segun la opcion selecionada****//
    @FXML
    void cbOrdenSalarioOnAction(ActionEvent event) {
    cbOrdenSalario.setOnAction( e ->{
        switch (cbOrdenSalario.getValue().toString()){
            case "MAYOR":
                //TODO
                System.out.println("a");
                break;
            case "MENOR":
                //TODO
                System.out.println("b");
                break;
            case "DEFECTO":
                //TODO
                System.out.println("c");
                break;
        }
    });
    }

    @FXML
    public void initialize() throws SQLException {
        //****Cargo al inicio el estilo del combobox****//
        cbOrdenSalario.getItems().addAll("MAYOR", "MENOR", "DEFECTO");
        cbOrdenSalario.getSelectionModel().select("DEFECTO");
        cbOrdenSalario.setStyle("-fx-font: 16px  \"Georgia\";" +
                "-fx-background-color:  #ffc806;"+
                " -fx-font-weight: bold;");
        //****Recupero el nombre del usuario que se ha logueado****//
        laSaludoUsuario.setText(laSaludoUsuario.getText()+" "+getUserLogin());//La forma en la que se me ha ocurrido recuperar el nombre de usuario.
        tfUsuario.setText(getUserLogin()); // valor por defecto al inicio de sesion es el nombre del usuario que se loguea.
        if (isSuperUsuario(getUserLogin())){

        }


    }

}
