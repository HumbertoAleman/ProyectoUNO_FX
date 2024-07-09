package com.ucab.proyectouno_fx.Model.Controlador.Guardador;

import com.google.gson.Gson;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;


public class GuardadorGson implements Guardador {
    public GuardadorGson() {
        gson = new Gson();
    }

    private final Gson gson;

    /**
     * Metodo que guarda el juego
     *
     * @param listaJugadores Lista de jugadores a guardar
     * @param pilaJugar      Pila de Cartas Jugadas a guardar
     * @param pilaTomar      Pila de Cartas para Tomar a guardar
     * @param saltarTurno    Valor de verdad si se salta el turno del siguiente jugador
     * @param cartasATomar   Valor de verdad de la cantidad de cartas a tomar
     * @throws IOException Se lanza la excepcion si ocurre un error guardando el archivo
     */
    public void guardarJuego(String directorioCargado, Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException {
        String listaJugadoresGuardar, pilaJugarGuardar, pilaTomarGuardar, juegoGuardar;

        String dirName = directorioCargado + "/";

        listaJugadoresGuardar = gson.toJson(listaJugadores);
        pilaJugarGuardar = gson.toJson(pilaJugar);
        pilaTomarGuardar = gson.toJson(pilaTomar);

        FileWriter FWJugadores = new FileWriter(dirName + "listaJugadores.json");
        StringWriter SWJugadores = new StringWriter();
        SWJugadores.write(listaJugadoresGuardar);
        FWJugadores.write(SWJugadores.toString());
        FWJugadores.close();

        FileWriter FWPilaJugar = new FileWriter(dirName + "pilaJugar.json");
        StringWriter SWPilaJugar = new StringWriter();
        SWPilaJugar.write(pilaJugarGuardar);
        FWPilaJugar.write(SWPilaJugar.toString());
        FWPilaJugar.close();

        FileWriter FWPilaTomar = new FileWriter(dirName + "pilaTomar.json");
        StringWriter SWPilaTomar = new StringWriter();
        SWPilaTomar.write(pilaTomarGuardar);
        FWPilaTomar.write(SWPilaTomar.toString());
        FWPilaTomar.close();

        FileWriter FWJuego = new FileWriter(dirName + "juego.json");
        StringWriter SWJuego = new StringWriter();
        juegoGuardar = String.format("{\"saltarTurno\":%b, \"cartasATomar\":%o }", saltarTurno, cartasATomar);
        SWJuego.write(juegoGuardar);
        FWJuego.write(SWJuego.toString());
        FWJuego.close();

    }

    /**
     * Metodo que guarda las puntuaciones del jugador
     *
     * @param directorioCargado Directorio a guardar la informacion
     * @param scoreList         Lista de puntuaciones a guardar
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    @Override
    public void guardarPuntuacion(String directorioCargado, List<Score> scoreList) throws IOException {
        String dirName = directorioCargado + "/";
        String scoresGuardar = gson.toJson(scoreList);
        FileWriter FWScores = new FileWriter(dirName + "scores.json");
        StringWriter SWScores = new StringWriter();
        SWScores.write(scoresGuardar);
        FWScores.write(SWScores.toString());
        FWScores.close();
    }
}
