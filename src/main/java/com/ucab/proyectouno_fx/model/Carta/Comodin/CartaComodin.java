package com.ucab.proyectouno_fx.model.Carta.Comodin;

import com.ucab.proyectouno_fx.model.Carta.Carta;

public abstract class CartaComodin implements Carta {

    protected char colorSeleccionado = 'C';

    /**
     * Obtiene el color de la carta
     *
     * @return color de la carta
     */
    public char getColor() {
        return colorSeleccionado;
    }
    /**
     * Cambia el color de la carta
     *
     * @param colorSeleccionado color a cambiar
     */
    public void setColorSeleccionado(char colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }
    protected String tipo;

    /**
     * Constructor de la carta comodin
     *
     * @param tipo tipo de la carta
     */
    public CartaComodin(String tipo) {
        this.tipo = tipo;
    }

    private static void cambiarColorDialogo() {
        System.out.println("Colores: ");
        System.out.println("R. Rojo");
        System.out.println("B. Azul");
        System.out.println("Y. Amarillo");
        System.out.println("G. Verde");
        System.out.println();
        System.out.print("Ingrese el color a jugar: ");
    }

    public void changeColor() { }

    @Override
    public String getEtiqueta() {
        return getColor() + getTipo();
    }
}
