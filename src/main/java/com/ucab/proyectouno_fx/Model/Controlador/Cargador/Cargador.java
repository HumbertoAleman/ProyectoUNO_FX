package com.ucab.proyectouno_fx.Model.Controlador.Cargador;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public interface Cargador {
    void cargarJuego(Juego juego, String playerDataPath) throws IOException, ParseException;
}
