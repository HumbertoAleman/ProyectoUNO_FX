package com.ucab.proyectouno_fx.Model.Controlador.Cargador;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface Cargador {
    /**
     * Metodo de la interfaz para cargar el juego
     *
     * @param juego          Juego a cargar
     * @param playerDataPath Camino al directorio de la informacion del jugador
     * @throws IOException    Excepcion a ser lanzada en caso de un error de IO
     * @throws ParseException Excepcion a ser lanzada en caso de un error de JSON
     */
    void cargarJuego(Juego juego, String playerDataPath) throws IOException, ParseException;

    /**
     * Metodo de la interfaz para cargar las puntuaciones
     *
     * @param juego          Juego a agregar las puntuaciones a cargar
     * @param playerDataPath Camino al directorio de la informacion del jugador
     * @throws IOException    Excepcion a ser lanzada en caso de un error de IO
     * @throws ParseException Excepcion a ser lanzada en caso de un error de JSON
     */
    void cargarScores(Juego juego, String playerDataPath) throws IOException, ParseException;
}
