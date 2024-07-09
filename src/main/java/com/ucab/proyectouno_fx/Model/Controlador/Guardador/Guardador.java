package com.ucab.proyectouno_fx.Model.Controlador.Guardador;

import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import java.io.IOException;
import java.util.List;

public interface Guardador {
    /**
     * Metodo de la interfaz para guardar el juego
     *
     * @param directorioCargado Directorio a guardar la informacion
     * @param listaJugadores    Lista de jugadores a guardar
     * @param pilaJugar         Pila de cartas jugadas a guardar
     * @param pilaTomar         Pila de cartas para tomar a guardar
     * @param saltarTurno       Si el turno actual se esta saltando
     * @param cartasATomar      La cantidad de cartas que debe tomar el proximo jugador
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    void guardarJuego(String directorioCargado, Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException;

    /**
     * Metodo de la interfaz para guardar el juego
     *
     * @param directorioCargado Directorio a guardar la informacion
     * @param scoreList         Lista de puntuaciones a guardar
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    void guardarPuntuacion(String directorioCargado, List<Score> scoreList) throws IOException;
}