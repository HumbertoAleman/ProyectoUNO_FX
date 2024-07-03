package com.ucab.proyectouno_fx.model.Carta.Comodin;

import com.ucab.proyectouno_fx.model.Controlador.Juego;

public class CartaMasCuatro extends CartaComodin {
    private static final String tipo = "T4";

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

    /**
     * Ejecuta la accion de la carta mas cuatro
     */
    @Override
    public void ejecutarAccion() {
        changeColor();
        Juego.setCartasATomar(Juego.getCartasATomar() + 4);
    }

    /**
     * Obtiene el tipo de la carta mas cuatro
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }

    /**
     * Muestra la carta mas cuatro
     */
    @Override
    public void mostrarCarta() {
        System.out.print(this.getColor() + tipo + "  ");
    }
}