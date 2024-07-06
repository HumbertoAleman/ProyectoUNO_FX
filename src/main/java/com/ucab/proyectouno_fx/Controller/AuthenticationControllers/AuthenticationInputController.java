package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Model.Controlador.ManejadorSesion;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthenticationInputController extends ControllerParent {
    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    private final ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();

    @FXML
    public void authenticateUser(ActionEvent event) throws IOException {
        boolean authenticated = manejadorSesion.loginPlayerDirectory(textField.getText(), passwordField.getText());

        if (!authenticated) {
            System.out.println("ERROR: El usuario no esta autenticado");
            return;
        }

        System.out.println("Usuario autenticado correctamente");

        MainMenuController.setActiveUser(textField.getText());
        switchToScene(event, mainMenuView);
    }

    @FXML
    public void returnToRegisterAuthView(ActionEvent event) throws IOException {
        switchToScene(event, registerAuthView);
    }
}
