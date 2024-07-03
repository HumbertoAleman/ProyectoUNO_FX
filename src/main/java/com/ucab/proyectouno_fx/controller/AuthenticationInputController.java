package com.ucab.proyectouno_fx.controller;

import com.ucab.proyectouno_fx.HelloApplication;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthenticationInputController extends ControllerParent {
    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void registerUser(ActionEvent event) {
        System.out.println("Text field: " + textField.getText());
        System.out.println("Password field: " + passwordField.getText());
    }

    @FXML
    public void authenticateUser(ActionEvent event) throws IOException {
        // TODO: Agregar logica de autenticacion aqui
        boolean authenticated = true;

        System.out.println("User is authenticated");
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
