package com.ucab.proyectouno_fx.Model.Controlador.Score;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que se encarga de manejar los puntajes de los jugadores
 */
public class ScoreManager {
    /**
     * Constructor vacio de la clase ScoreManager
     */
    public ScoreManager() {

    }

    /**
     * Constructor de la clase ScoreManager
     *
     * @param scoreList Lista de puntajes a cargar
     */
    public ScoreManager(List<Score> scoreList) {
        this.loadedScores.addAll(scoreList);
    }

    private final List<Score> loadedScores = new LinkedList<>();

    /**
     * Metodo para retornar la lista de puntuaciones
     *
     * @return Retorna la lista de puntuaciones
     */
    public List<Score> getScores() {
        return this.loadedScores;
    }

    /**
     * Metodo para agregar una puntuacion al manejador de puntuaciones
     *
     * @param score Puntuacion a agregar
     */
    public void addScore(Score score) {
        loadedScores.add(score);
    }

}
