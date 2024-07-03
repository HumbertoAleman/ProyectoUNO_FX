package com.ucab.proyectouno_fx.model.Carta;

public abstract class CartaColorada implements Carta {
    protected char color;

    /**
     * Constructor de la carta colorada
     */
    public CartaColorada() {
    }

    /**
     * Constructor de la carta colorada
     *
     * @param color color de la carta
     */
    public CartaColorada(char color) {
        this.color = color;
    }

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
