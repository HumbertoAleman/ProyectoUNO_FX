package com.ucab.proyectouno_fx.model.Carta.Accion;

import com.ucab.proyectouno_fx.model.Controlador.Juego;

public class CartaRevertir extends CartaAccion {
    private static final String tipo = "R";

    /**
     * Constructor de la carta revertir
     */
    public CartaRevertir() {
    }

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
        if (Juego.getNumeroJugadores() == 2) {
            Juego.getInstance().setSaltarTurno(true);
            return;
        }
        Juego.revertirOrden();
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

    /**
     * Muestra la carta revertir
     */
    @Override
    public void mostrarCarta() {
        System.out.print(this.color + tipo + "  ");
    }
}
