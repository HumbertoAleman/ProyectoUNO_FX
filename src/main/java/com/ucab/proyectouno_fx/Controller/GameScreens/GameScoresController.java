package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScoresController extends ControllerParent {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer.setText(MainMenuController.getActiveUser());
    }

    @FXML
    public Label currentPlayer;

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }
}
