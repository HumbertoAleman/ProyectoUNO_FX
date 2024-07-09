package com.ucab.proyectouno_fx.Model.Carta.Comodin;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

public class CartaMasCuatro extends CartaComodin {
    /**
     * Constructor de la carta mas cuatro
     */
    public CartaMasCuatro() {
        super(CartaMasCuatro.tipo);
    }

    /**
     * Constructor de la carta mas cuatro
     *
     * @param color color seleccionado
     */
    public CartaMasCuatro(Character color) {
        super(CartaMasCuatro.tipo);
        colorSeleccionado = color;
    }

    private static final int aTomar = 4;

    private static final String tipo = "T4";

    /**
     * Ejecuta la accion de la carta mas cuatro
     */
    @Override
    public void ejecutarAccion() {
        Juego.getInstance().increaseCartasATomar(aTomar);
    }
}