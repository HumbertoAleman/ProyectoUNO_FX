package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
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

    @FXML
    public Label puntuacionTotal;

    /**
     * Metodo que se encarga de regresar al menu principal
     *
     * @param resourceBundle Evento de la accion
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int totalScore = 0;
        boolean gray = false;
        Juego juego = Juego.getInstance();
        if (juego.getScoreManager() == null) {
            currentPlayer.setText(MainMenuController.getActiveUser());
            puntuacionTotal.setText(String.valueOf(totalScore));
            return;
        }
        for (Score score : juego.getScoreManager().getScores()) {
            Label winLabel, playerLabel, scoreLabel;
            winLabel = new Label(score.isWin() ? "GANADA" : "PERDIDA");
            playerLabel = new Label(score.getJugadorGanador().getNombre());
            scoreLabel = new Label(String.valueOf(score.getScore()));

            totalScore += score.isWin() ? score.getScore() : 0;
            VBox scoreBox = new VBox(winLabel, playerLabel, scoreLabel);
            scoreBox.setStyle("-fx-background-color: " + (gray ? "#FFFFFF80" : "#FFFFFF40"));
            gray = !gray;
            scoreList.getChildren().add(scoreBox);
        }

        scoreList.setSpacing(4.0);
        currentPlayer.setText(MainMenuController.getActiveUser());
        puntuacionTotal.setText(String.valueOf(totalScore));
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
