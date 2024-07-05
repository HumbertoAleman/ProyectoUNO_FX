package com.ucab.proyectouno_fx.model.Carta.Accion;

import com.ucab.proyectouno_fx.model.Controlador.Juego;

public class CartaSaltar extends CartaAccion {
    private static final String tipo = "S";

    /**
     * Constructor de la carta saltar
     *
     * @param color color de la carta
     */
    public CartaSaltar(char color) {
        super(color, tipo);
    }

    /**
     * Ejecuta la accion de la carta saltar
     */
    @Override
    public void ejecutarAccion() {
        System.out.println("Saltando turno del siguiente jugador");
        Juego.getInstance().setSaltarTurno(true);
        System.out.println(Juego.getInstance().isSaltarTurno());
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

    /**
     * Muestra la carta saltar
     */
    @Override
    public void mostrarCarta() {
        System.out.print(this.color + tipo + "  ");
    }
}
