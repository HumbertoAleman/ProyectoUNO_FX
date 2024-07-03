package com.ucab.proyectouno_fx.controller;

import com.sun.tools.javac.Main;
import com.ucab.proyectouno_fx.HelloApplication;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ControllerParent {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer.setText(activeUser);
    }

    private static String activeUser = "TEST PLAYER";

    public static void setActiveUser(String activeUser) {
        MainMenuController.activeUser = activeUser;
    }

    public static String getActiveUser() {
        return MainMenuController.activeUser;
    }

    @FXML
    private Label currentPlayer;

    @FXML
    public void newGame(ActionEvent event) throws IOException {
        // TODO: Implementar logica para crear nuevo juego
        switchToScene(event, gameScreenView);
    }

    @FXML
    public void loadGame(ActionEvent event) throws IOException {
        // TODO: Implementar logica para cargar juego
        switchToScene(event, gameScreenView);
    }

    @FXML
    public void gotoScoresView(ActionEvent event) throws IOException {
        switchToScene(event, gameScoresView);
    }

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        activeUser = null;
        switchToScene(event, registerAuthView);
    }
}
