package ventanaPrincipal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import login.Login;

import java.io.IOException;
import java.sql.SQLException;

import static login.LoginController.getUserLogin;
import static metodos.CompruebaDatos.*;
import static metodos.ModificaDatos.*;

public class PrincipalController {
    public static int num;
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
    @FXML
    private TextField tfSalario;
    @FXML
    private CheckBox ckbAdmin;
    @FXML
    private Label laSalario;
    @FXML
    private Button btnBorrarTodo;

    //*************Funcionamiento Controladores*************//

    //****Da de alta a un usuario****//
    @FXML
    void btnAltaOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        int admin = 0;
        if (ckbAdmin.isSelected()){
            admin = 1;
        }
        //si todos estos campos no estan vacios
        if (!(tfUsuario.getText().isEmpty() && pfContrasenya.getText().isEmpty() && tfSalario.getText().isEmpty())){
            //si usuario no existe añadelo a la base de datos
           if (!isUsuarioExistente(new Login(tfUsuario.getText()))){
               //se comprueba que existe, si es asi devuelve un mensaje
               if (isUsuarioExistente(anyadeUsuario(tfUsuario.getText(),pfContrasenya.getText(),Integer.parseInt(tfSalario.getText()),admin))){
                    taResultado.setText(tfUsuario.getText()+" Anyadido Satisfactoriamente");
                    btnExportar.setDisable(false);
               }
           }else{
               //si existe se limpian los campos y aviso
               tfUsuario.setText("");tfSalario.setText("");pfContrasenya.setText("");ckbAdmin.setSelected(false);
               laAvisos.setText("El usuario ya existe");
           }
        }

    }

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
            case "MENOR":
                num = 1;
                break;
            case "MAYOR":
                num = 2;
                break;
            case "DEFECTO":
                num = 0;
        }
    });
    }

    //*****Devolucion de los usuarios por pantalla*****//
    @FXML
    void verOnAction(ActionEvent event) throws SQLException {
        taResultado.setText(getDatosUsuarios());
        btnExportar.setDisable(false);

    }
    //****Boton modificar solo funciona si eres admin o tu nombre aparece en la casilla de usuario****//
    @FXML
    void btnModificarOnAction(ActionEvent event) throws SQLException {

        if ( (tfUsuario.getText().equals( getUserLogin()) )|| (isSuperUsuario(getUserLogin())) ){
            Login usuarioModificado = modificaUsuario(tfUsuario.getText(),pfContrasenya.getText(),Integer.parseInt(tfSalario.getText()),isSuperUsuario(tfUsuario.getText())?1:0);
            taResultado.setText(tfUsuario.getText()+" Modificado Satisfactoriamente");
            btnExportar.setDisable(false);
        }else{
            pfContrasenya.setText("");tfUsuario.setText(getUserLogin());
            laAvisos.setText("No tienes permisos para modificar otros usuarios");
        }
    }

    //*****Elimina si eres admin*****//
    @FXML
    void btnEliminarOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (isSuperUsuario(getUserLogin())){
            //si existe el usuario escrito y la contraseña coincide entonces puedes borrarlo
            if ((isUsuarioExistente(new Login(tfUsuario.getText()))) && (validateLogin(tfUsuario.getText() , pfContrasenya.getText()) == 1)){
                eliminaUsuario(new Login(tfUsuario.getText()));
                if (!isUsuarioExistente(new Login(tfUsuario.getText()))){
                    taResultado.setText("El usuario "+tfUsuario.getText()+" ha sido eliminado satisfactoriamente");
                    btnExportar.setDisable(false);
                }
            }else{
                laAvisos.setText("Datos incorrectos");
                tfUsuario.setText("");tfSalario.setText("");pfContrasenya.setText("");ckbAdmin.setSelected(false);
            }
        }

    }

    //*****'Controla' si se ha pulsado la tecla mayuscula (va un poco de lado)*****//
    public void btnControlaMayus(KeyEvent keyEvent) {
        if (keyEvent.getText() == keyEvent.getText().toUpperCase()){
            laAvisos.setText("Bloq Mayus Activado");
        } else{
            laAvisos.setText("");
        }


    }


    @FXML
    public void initialize() throws SQLException {

        Login usuario = new Login(getUserLogin());

        //****Cargo al inicio el estilo del combobox****//
        cbOrdenSalario.getItems().addAll("MAYOR", "MENOR", "DEFECTO");
        cbOrdenSalario.getSelectionModel().select("DEFECTO");
        cbOrdenSalario.setStyle("-fx-font: 16px  \"Georgia\";" +
                "-fx-background-color:  #ffc806;"+
                " -fx-font-weight: bold;");

        //****Recupero datos del usuario que se ha logueado****//
        laSaludoUsuario.setText(laSaludoUsuario.getText()+" "+getUserLogin());//La forma en la que se me ha ocurrido recuperar el nombre de usuario.
        tfUsuario.setText(getUserLogin()); // valor por defecto al inicio de sesion es el nombre del usuario que se loguea.
        tfSalario.setText(String.valueOf(usuario.getSalario()));
        numericOnly(tfSalario); //Solo se permiten valores numericos

        btnExportar.setDisable(true);

        if (!isSuperUsuario(getUserLogin())){
            btnAlta.setDisable(true);
            BtnEliminar.setDisable(true);
            ckbAdmin.setDisable(true);
            tfSalario.setDisable(true);
            btnBorrarTodo.setDisable(true);

            btnBorrarTodo.setVisible(false);

        }else{
            ckbAdmin.setSelected(true);
        }
        //Solo lectura
        taResultado.setEditable(false);


    }

}
