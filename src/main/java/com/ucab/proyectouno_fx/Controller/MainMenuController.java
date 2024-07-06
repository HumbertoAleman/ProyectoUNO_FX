package com.ucab.proyectouno_fx.Controller;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;
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
        Juego.getInstance().initializeGame();
        switchToScene(event, gameScreenView);
    }

    @FXML
    public void loadGame(ActionEvent event) throws IOException {
        try {
            Juego.getInstance().cargarJuego();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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
