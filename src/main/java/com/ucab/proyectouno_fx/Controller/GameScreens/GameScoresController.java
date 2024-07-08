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

public class GameScoresController extends ControllerParent {
    @FXML
    public VBox scoreList;

    @FXML
    public Label currentPlayer;

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

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }
}
