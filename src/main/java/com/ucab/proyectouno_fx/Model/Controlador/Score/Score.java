package com.ucab.proyectouno_fx.Model.Controlador.Score;

import com.ucab.proyectouno_fx.Model.Jugador.Jugador;

public class Score {
    private final Jugador jugadorGanador;

    private final int score;

    private final boolean win;

    public Score(boolean win, Jugador jugadorGanador, int score) {
        this.jugadorGanador = jugadorGanador;
        this.score = score;
        this.win = win;
        System.out.println("Registrando puntuacion: ");
        System.out.println("Partida " + (win ? "Ganada" : "Perdida"));
        System.out.println("Jugador Ganador :" + jugadorGanador.getNombre());
        System.out.println("Puntuacion: " + score);
    }

    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }

    public int getScore() {
        return score;
    }

    public boolean isWin() {
        return win;
    }
}
