package com.example.javafx_prueba;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    //Recibe un Stage que es el JFrame
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        // Scene es el Panel
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // Titulo de la aplicacion
        stage.setTitle("Hello!");
        //El panel en el frame
        stage.setScene(scene);
        //Muestra ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}