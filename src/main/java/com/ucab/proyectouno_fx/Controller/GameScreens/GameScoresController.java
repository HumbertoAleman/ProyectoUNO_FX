package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controladora de la vista de puntajes
 */
public class GameScoresController extends ControllerParent {
    @FXML
    public VBox scoreList;

    @FXML
    public Label currentPlayer;

    /**
     * Metodo que se encarga de regresar al menu principal
     *
     * @param resourceBundle Evento de la accion
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Juego juego = Juego.getInstance();

        for (Score score : juego.getScoreManager().getScores()) {
            Label winLabel, playerLabel, scoreLabel;
            winLabel = new Label(score.isWin() ? "GANADA" : "PERDIDA");
            playerLabel = new Label(score.getJugadorGanador().getNombre());
            scoreLabel = new Label(String.valueOf(score.getScore()));

            scoreList.getChildren().add(new VBox(winLabel, playerLabel, scoreLabel));
        }
        currentPlayer.setText(MainMenuController.getActiveUser());
    }

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
}
