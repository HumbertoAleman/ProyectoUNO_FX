package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jugador {
    /**
     * Costructor de humano
     *
     * @param nombre nombre del jugador
     */
    public Humano(String nombre) {
        super(nombre, "H");
    }

    /**
     * El jugador realiza una accion en su turno
     *
     * @deprecated
     */
    @Override
    public void tomarTurno() {
        Juego juego = Juego.getInstance();
        Scanner scanner = new Scanner(System.in);
        Carta cartaSeleccionada = null;
        String seleccion = "";

        while (!juego.jugarCarta(cartaSeleccionada) && !seleccion.equals("T") && !seleccion.equals("0")) {
            System.out.println();
            System.out.println("Es el turno de: " + getNombre());
            if (juego.getCartasATomar() > 0)
                System.out.println("Ingrese T para tomar " + juego.getCartasATomar() + " cartas");
            else System.out.println("Ingrese T para tomar una carta");
            System.out.println();
            System.out.println("Presione 0 para regresar al menu principal");

            System.out.print("Escriba la carta a jugar: ");
            seleccion = scanner.nextLine().toUpperCase();

            // Esta linea existe porque por alguna razon si usamos una variable dentro de una lamda function
            // tiene que ser de esta manera
            String finalSeleccion = seleccion;
            cartaSeleccionada = mazo.stream().filter(carta -> (carta.getColor() + carta.getTipo()).equals(finalSeleccion)).findFirst().orElse(null);
        }

        if (seleccion.equals("T")) {
            ArrayList<Carta> cartasAgregadas = juego.darCartasAJugadorActual();
            if (cartasAgregadas.size() == 1) {
                Carta cartaTomada = cartasAgregadas.getFirst();
                while (!seleccion.equals("S") && !seleccion.equals("N")) {
                    System.out.println("La carta tomada fue ");
                    System.out.println("+---+");
                    System.out.println();
                    System.out.println("+---+");
                    System.out.println();
                    System.out.println("Desea jugar esta carta?");
                    System.out.println("S. Si");
                    System.out.println("N. No");
                    System.out.println();
                    System.out.print("Seleccion ");
                    seleccion = scanner.nextLine().toUpperCase();
                }

                if (seleccion.equals("N")) return;

                if (juego.jugarCarta(cartaTomada)) {
                    System.out.println("Si se pudo jugar la carta tomada");
                    mazo.remove(cartaTomada);
                } else System.out.println("No se pudo jugar la carta tomada");
                return;
            }
            return;
        }

        mazo.remove(cartaSeleccionada);
    }
}
