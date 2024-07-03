package com.ucab.proyectouno_fx.model.Carta.Comodin;

import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Controlador.Juego;

import java.util.Random;
import java.util.Scanner;

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

    protected void changeColor() {
        if (Juego.jugadorActualEsCPU()) {
            Random rand = new Random();
            final String colores = "RBYG";
            this.colorSeleccionado = colores.charAt(rand.nextInt(colores.length()));
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (this.colorSeleccionado == 'C') {
            // Juego.limpiarConsola();
            cambiarColorDialogo();
            switch (scanner.nextLine().toLowerCase()) {
                case "r":
                case "rojo":
                case "red":
                    this.colorSeleccionado = 'R';
                    break;
                case "b":
                case "blue":
                case "azul":
                    this.colorSeleccionado = 'B';
                    break;
                case "y":
                case "yellow":
                case "amarillo":
                    this.colorSeleccionado = 'Y';
                    break;
                case "g":
                case "green":
                case "verde":
                    this.colorSeleccionado = 'G';
                    break;
                default:
                    this.colorSeleccionado = 'C';
                    System.out.println();
                    System.out.println("La opcion seleccionada es invalida");
                    scanner.nextLine();
            }
        }
    }

    @Override
    public String getEtiqueta() {
        return getColor() + getTipo();
    }
}
