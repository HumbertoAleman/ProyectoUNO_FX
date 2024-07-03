package com.ucab.proyectouno_fx.controller;

import com.ucab.proyectouno_fx.HelloApplication;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAuthController extends ControllerParent {
    @FXML
    public void onRegisterButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, registerInputViewFile);
    }

    @FXML
    public void onAuthButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, authenticationInputViewFile);
    }

    @FXML
    public void onExitButtonPress(ActionEvent event) {
        exitApp();
    }
}
