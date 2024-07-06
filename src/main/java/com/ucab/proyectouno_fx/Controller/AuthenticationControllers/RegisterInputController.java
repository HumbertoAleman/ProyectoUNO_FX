package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Model.Controlador.ManejadorSesion;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterInputController extends ControllerParent {
    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    private final ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();

    @FXML
    public void registerUser() {
        System.out.println("Text field: " + textField.getText());
        System.out.println("Password field: " + passwordField.getText());

        manejadorSesion.registerPlayerDirectory(textField.getText(), passwordField.getText());
    }

    @FXML
    public void returnToRegisterAuthView(ActionEvent event) throws IOException {
        switchToScene(event, registerAuthView);
    }
}
