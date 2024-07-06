package com.ucab.proyectouno_fx.Model.Carta.Accion;

import com.ucab.proyectouno_fx.Model.Carta.CartaColorada;

public abstract class CartaAccion extends CartaColorada {
    protected String tipo;

    @Override
    public int getScore() {
        return 20;
    }

    /**
     * Constructor de la carta accion
     *
     * @param color color de la carta
     * @param tipo  tipo de la carta
     */
    public CartaAccion(char color, String tipo) {
        super(color);
        this.tipo = tipo;
    }

}
