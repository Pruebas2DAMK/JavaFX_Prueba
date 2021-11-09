package com.example.javafx_prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class Controller {
    //*************OBJETOS***************//
    @FXML
    private Label welcomeText;

    //**********HAGO*COSICAS************//
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}