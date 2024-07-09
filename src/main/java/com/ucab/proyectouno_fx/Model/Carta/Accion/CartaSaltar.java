package com.ucab.proyectouno_fx.Model.Carta.Accion;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

public class CartaSaltar extends CartaAccion {
    /**
     * Constructor de la carta saltar
     *
     * @param color color de la carta
     */
    public CartaSaltar(char color) {
        super(color, tipo);
    }

    private static final String tipo = "S";

    /**
     * Ejecuta la accion de la carta saltar
     */
    @Override
    public void ejecutarAccion() {
        Juego.getInstance().setSaltarTurno(true);
    }

    /**
     * Obtiene el tipo de la carta saltar
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }
}
