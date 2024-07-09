package com.ucab.proyectouno_fx.Model.Carta.Comodin;

import com.ucab.proyectouno_fx.Model.Carta.Carta;

public abstract class CartaComodin implements Carta {
    /**
     * Constructor de la carta comodin
     *
     * @param tipo tipo de la carta
     */
    public CartaComodin(String tipo) {
        this.tipo = tipo;
    }

    private static final int score = 50;

    protected final String tipo;

    protected char colorSeleccionado = 'C';

    /**
     * Obtiene el color de la carta
     *
     * @return color de la carta
     */
    public char getColor() {
        return colorSeleccionado;
    }

    /**
     * Cambia el color de la carta
     *
     * @param colorSeleccionado color a cambiar
     */
    public void setColorSeleccionado(char colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }

    /**
     * Retorna el color y el tipo de la carta
     *
     * @return El String del color y el tipo de carta
     */
    @Override
    public String getEtiqueta() {
        return getColor() + getTipo();
    }

    /**
     * Retorna la puntuacion que da la carta al final del juego
     *
     * @return puntuacion final de la carta
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Obtiene el tipo de la carta comodin
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }
}
