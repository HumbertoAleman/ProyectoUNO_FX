package com.ucab.proyectouno_fx.Model.Carta.Accion;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

public class CartaRevertir extends CartaAccion {
    /**
     * Constructor de la carta revertir
     *
     * @param color color de la carta
     */
    public CartaRevertir(char color) {
        super(color, CartaRevertir.tipo);
    }

    private static final String tipo = "R";

    /**
     * Ejecuta la accion de la carta revertir
     */
    @Override
    public void ejecutarAccion() {
        Juego.getInstance().revertirOrden();
    }

    /**
     * Obtiene el tipo de la carta revertir
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }
}
