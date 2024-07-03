package com.ucab.proyectouno_fx.model.Carta;

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

    String getEtiqueta();

    /**
     * Muestra la carta
     */
    void mostrarCarta();
}
