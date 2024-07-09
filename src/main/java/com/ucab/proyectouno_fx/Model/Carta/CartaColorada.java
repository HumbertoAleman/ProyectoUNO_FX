package com.ucab.proyectouno_fx.Model.Carta;

public abstract class CartaColorada implements Carta {
    /**
     * Constructor de la carta colorada
     */
    protected CartaColorada() {
    }

    /**
     * Constructor de la carta colorada
     *
     * @param color color de la carta
     */
    protected CartaColorada(char color) {
        this.color = color;
    }

    /**
     * Color de la carta colorada
     */
    protected char color;

    /**
     * Obtiene el color de la carta
     *
     * @return color de la carta
     */
    public char getColor() {
        return color;
    }

    @Override
    public String getEtiqueta() {
        return getColor() + getTipo();
    }
}
