package com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controladora de la vista de perdedor
 */
public class LoserViewController extends ControllerParent {
    private static int puntuacionFinal;

    public static void setPuntuacionFinal(int puntuacionFinal) {
        LoserViewController.puntuacionFinal = puntuacionFinal;
    }

    @FXML
    public Label puntuacionFinalLabel;

    /**
     * Metodo que se encarga de regresar al menu principal
     *
     * @param event Evento de la accion
     * @throws IOException Excepcion lanzada si hay un error en la lectura del archivo
     */
    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }

    /**
     * Metodo que se encarga de inicializar la vista
     *
     * @param url            URL de la vista
     * @param resourceBundle Recurso de la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntuacionFinalLabel.setText(String.valueOf(puntuacionFinal));
    }
}
