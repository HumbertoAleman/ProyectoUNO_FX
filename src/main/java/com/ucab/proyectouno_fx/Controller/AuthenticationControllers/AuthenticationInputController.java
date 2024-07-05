package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
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

    @FXML
    public void authenticateUser(ActionEvent event) throws IOException {
        // TODO: Agregar logica de autenticacion aqui
        boolean authenticated = true;

        System.out.println("User " + textField.getText() + " is authenticated");
        System.out.println("Password: " + passwordField.getText());
        if (!authenticated) {
            // TODO: Encontrar razon de porque no esta autenticado (no existe el usuario, contraseña incorrecta, otro)
            // TODO: Agregar una manera visual de mostrar que no estas autenticado

            System.out.println("ERROR: El usuario no esta autenticado");
            return;
        }

        MainMenuController.setActiveUser(textField.getText());
        switchToScene(event, mainMenuView);
    }

    @FXML
    public void returnToRegisterAuthView(ActionEvent event) throws IOException {
        switchToScene(event, registerAuthView);
    }
}
