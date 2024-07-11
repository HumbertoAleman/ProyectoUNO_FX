package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Clase controladora de la vista de puntajes
 */
public class GameScoresController extends ControllerParent {
    @FXML
    public GridPane scoreTable;

    private final Map<String, Integer> scores = new HashMap<>();

    /**
     * Metodo que se encarga de regresar al menu principal
     *
     * @param resourceBundle Evento de la accion
     */
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Juego juego = Juego.getInstance();

        if (juego.getScoreManager() == null) return;

        for (Score score : juego.getScoreManager().getScores()) {
            if (!scores.containsKey(score.getJugadorGanador().getNombre())) {
                scores.put(score.getJugadorGanador().getNombre(), score.getScore());
                continue;
            }
            scores.put(score.getJugadorGanador().getNombre(), score.getScore() + scores.get(score.getJugadorGanador().getNombre()));
        }

        // Sorteamos
        List<Map.Entry<String, Integer>> list = new LinkedList<>(scores.entrySet());
        list.sort(Map.Entry.comparingByValue());
        HashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> me : list.reversed())
            result.put(me.getKey(), me.getValue());

        // Creamos
        for (String jugadorNombre : result.keySet()) {
            scoreTable.add(new Label(jugadorNombre), 0, scoreTable.getRowCount());
            scoreTable.add(new Label(scores.get(jugadorNombre).toString()), 1, scoreTable.getRowCount()-1);
        }
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
