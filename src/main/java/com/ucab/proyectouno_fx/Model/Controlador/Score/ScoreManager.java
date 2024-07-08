package com.ucab.proyectouno_fx.Model.Controlador.Score;

import java.util.LinkedList;
import java.util.List;

public class ScoreManager {
    private final List<Score> loadedScores = new LinkedList<>();

    public List<Score> getScores() {
        return this.loadedScores;
    }

    public void addScore(Score score) {
        loadedScores.add(score);
    }

    public ScoreManager() {

    }

    public ScoreManager(List<Score> scoreList) {
        this.loadedScores.addAll(scoreList);
    }
}
