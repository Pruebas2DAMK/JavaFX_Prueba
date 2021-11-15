package metodos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ventana {

    //****Crea la ventana de manejo de datos****//
    public void creaVentanaPrincipal(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ventanaPrincipal/principal.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DragonBaseFX");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("img/db.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

}
