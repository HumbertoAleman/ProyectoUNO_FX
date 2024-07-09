package com.ucab.proyectouno_fx.Model.Carta;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

public interface Carta {
    /**
     * Ejecuta la accion de la carta
     */
    void ejecutarAccion();

    /**
     * Obtiene el color de la carta
     *
     * @return color de la carta
     */
    char getColor();

    /**
     * Obtiene el tipo de la carta
     *
     * @return tipo de la carta
     */
    String getTipo();

    int getScore();

    String getEtiqueta();
}
