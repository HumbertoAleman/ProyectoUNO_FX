package com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinnerViewController extends ControllerParent {
    private static int puntuacionFinal;

    public static void setPuntuacionFinal(int puntuacionFinal) {
        WinnerViewController.puntuacionFinal= puntuacionFinal;
    }

    @FXML
    public Label puntuacionFinalLabel;

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntuacionFinalLabel.setText(String.valueOf(puntuacionFinal));
    }
}
