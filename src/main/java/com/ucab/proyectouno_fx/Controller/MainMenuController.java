package com.ucab.proyectouno_fx.Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

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
