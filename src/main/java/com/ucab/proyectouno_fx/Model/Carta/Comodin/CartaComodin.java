package com.ucab.proyectouno_fx.Model.Carta.Comodin;

import com.ucab.proyectouno_fx.Model.Carta.Carta;

public abstract class CartaComodin implements Carta {

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
    protected String tipo;

    /**
     * Constructor de la carta comodin
     *
     * @param tipo tipo de la carta
     */
    public CartaComodin(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getEtiqueta() {
        return getColor() + getTipo();
    }

    @Override
    public int getScore() {
        return 50;
    }
}
