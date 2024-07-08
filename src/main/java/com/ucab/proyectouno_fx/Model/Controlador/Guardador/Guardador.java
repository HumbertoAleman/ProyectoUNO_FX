package com.ucab.proyectouno_fx.Model.Controlador.Guardador;

import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import java.io.IOException;
import java.util.List;

public interface Guardador {
    void guardarJuego(String directorioCargado, Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException;
    void guardarPuntuacion(String directorioCargado, List<Score> scoreList) throws IOException;
}