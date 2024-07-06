package com.ucab.proyectouno_fx.Model.Controlador.Guardador;

import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import java.io.IOException;

public interface Guardador {
    void guardarJuego(String directorioCargado, Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException;
}