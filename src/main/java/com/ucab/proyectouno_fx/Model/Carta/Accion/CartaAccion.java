package com.ucab.proyectouno_fx.Model.Carta.Accion;

import com.ucab.proyectouno_fx.Model.Carta.CartaColorada;

public abstract class CartaAccion extends CartaColorada {
    /**
     * Constructor de la carta accion
     *
     * @param color color de la carta
     * @param tipo  tipo de la carta
     */
    protected CartaAccion(char color, String tipo) {
        super(color);
        this.tipo = tipo;
    }

    private static final int score = 20;

    protected final String tipo;

    /**
     * Retorna la puntuacion que da la carta al final del juego
     *
     * @return puntuacion final de la carta
     */
    @Override
    public int getScore() {
        return score;
    }
}
