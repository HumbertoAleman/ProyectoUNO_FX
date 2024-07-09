package com.ucab.proyectouno_fx.Model.Carta.Accion;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

public class CartaMasDos extends CartaAccion {
    /**
     * Constructor de la carta mas dos
     *
     * @param color color de la carta
     */
    public CartaMasDos(char color) {
        super(color, CartaMasDos.tipo);
    }

    private static final int aTomar = 2;

    private static final String tipo = "T2";

    /**
     * Ejecuta la accion de la carta mas dos
     */
    @Override
    public void ejecutarAccion() {
        Juego.getInstance().increaseCartasATomar(aTomar);
    }

    /**
     * Obtiene el tipo de la carta mas dos
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }
}
