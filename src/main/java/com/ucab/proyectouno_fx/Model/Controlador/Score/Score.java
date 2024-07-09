package com.ucab.proyectouno_fx.Model.Controlador.Score;

import com.ucab.proyectouno_fx.Model.Jugador.Jugador;

/**
 * Clase que representa el puntaje de una partida
 */
public class Score {
    private final Jugador jugadorGanador;

    private final int score;

    private final boolean win;

    /**
     * Constructor de la clase
     *
     * @param win            Si la partida fue ganada o perdida
     * @param jugadorGanador Jugador que gano la partida
     * @param score          Puntuacion de la partida
     */
    public Score(boolean win, Jugador jugadorGanador, int score) {
        this.jugadorGanador = jugadorGanador;
        this.score = score;
        this.win = win;
        System.out.println("Registrando puntuacion: ");
        System.out.println("Partida " + (win ? "Ganada" : "Perdida"));
        System.out.println("Jugador Ganador: " + jugadorGanador.getNombre());
        System.out.println("Puntuacion: " + score);
    }

    /**
     * Obtiene el jugador ganador
     *
     * @return Jugador ganador
     */
    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }

    /**
     * Obtiene la puntuacion
     *
     * @return Puntuacion
     */
    public int getScore() {
        return score;
    }

    /**
     * Obtiene si la partida fue ganada
     *
     * @return Si la partida fue ganada
     */
    public boolean isWin() {
        return win;
    }
}
