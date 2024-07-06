package com.ucab.proyectouno_fx.Model.Carta.Accion;

public class CartaRevertir extends CartaAccion {
    private static final String tipo = "R";

    /**
     * Constructor de la carta revertir
     *
     * @param color color de la carta
     */
    public CartaRevertir(char color) {
        super(color, CartaRevertir.tipo);
    }

    /**
     * Ejecuta la accion de la carta revertir
     * Si la cantidad de jugadores es igual a 2, la carta se comporta de manera identica a la carta saltar
     * Si la cantidad de jugadores es mayor a 2, la carta revierte el orden del juego
     */
    @Override
    public void ejecutarAccion() {
        System.out.println("Revirtiendo el flow del juego");
        juego.revertirOrden();
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
