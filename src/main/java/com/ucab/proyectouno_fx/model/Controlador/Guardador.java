package com.ucab.proyectouno_fx.model.Controlador;

import com.ucab.proyectouno_fx.model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.model.Jugador.Jugadores;

import com.google.gson.Gson;

import java.io.*;


public interface Guardador {
    Gson gson = new Gson();

    /**
     * Metodo que guarda el juego
     * @param listaJugadores Lista de jugadores a guardar
     * @param pilaJugar Pila de Cartas Jugadas a guardar
     * @param pilaTomar Pila de Cartas para Tomar a guardar
     * @param saltarTurno Valor de verdad si se salta el turno del siguiente jugador
     * @param cartasATomar Valor de verdad de la cantidad de cartas a tomar
     * @throws IOException Se lanza la excepcion si ocurre un error guardando el archivo
     */
    static void guardarJuego(Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException {
        String listaJugadoresGuardar;
        String pilaJugarGuardar;
        String pilaTomarGuardar;
        String juegoGuardar;

        listaJugadoresGuardar = gson.toJson(listaJugadores);
        pilaJugarGuardar = gson.toJson(pilaJugar);
        pilaTomarGuardar = gson.toJson(pilaTomar);

        FileWriter FWJugadores = new FileWriter("listaJugadores.json");
        StringWriter SWJugadores = new StringWriter();
        SWJugadores.write(listaJugadoresGuardar);
        FWJugadores.write(SWJugadores.toString());
        FWJugadores.close();

        FileWriter FWPilaJugar = new FileWriter("pilaJugar.json");
        StringWriter SWPilaJugar = new StringWriter();
        SWPilaJugar.write(pilaJugarGuardar);
        FWPilaJugar.write(SWPilaJugar.toString());
        FWPilaJugar.close();

        FileWriter FWPilaTomar = new FileWriter("pilaTomar.json");
        StringWriter SWPilaTomar = new StringWriter();
        SWPilaTomar.write(pilaTomarGuardar);
        FWPilaTomar.write(SWPilaTomar.toString());
        FWPilaTomar.close();

        FileWriter FWJuego = new FileWriter("juego.json");
        StringWriter SWJuego = new StringWriter();
        juegoGuardar  = String.format("{\"saltarTurno\":%b, \"cartasATomar\":%o }", saltarTurno, cartasATomar);
        SWJuego.write(juegoGuardar);
        FWJuego.write(SWJuego.toString());
        FWJuego.close();
    }
}
