package com.ucab.proyectouno_fx.Model.Carta.Accion;

public class CartaMasDos extends CartaAccion {
    private static final int aTomar = 2;

    private static final String tipo = "T2";

    /**
     * Constructor de la carta mas dos
     *
     * @param color color de la carta
     */
    public CartaMasDos(char color) {
        super(color, CartaMasDos.tipo);
    }

    /**
     * Ejecuta la accion de la carta mas dos
     */
    @Override
    public void ejecutarAccion() {
        juego.increaseCartasATomar(aTomar);
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
