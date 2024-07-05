package com.ucab.proyectouno_fx.model.Jugador;

import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Controlador.Juego;

import java.util.ArrayList;

public class Computador extends Jugador {
    private Juego juego = Juego.getInstance();

    /**
     * Constructor Computador
     *
     * @param nombre nombre del CPU
     */
    public Computador(String nombre) {
        super(nombre);
        tipo = "C";
    }

    private void mostrarTomarDecision() {
        ImpresoraCarta.mostrarMazo(mazo, false);
        System.out.print("El oponente esta tomando una seleccion");
        for (int i = 0; i < 3; i++) {
            esperar(500);
            System.out.print(".");
        }
        esperar(750);
        System.out.println();
    }

    /**
     * El computador realiza una accion en su turno
     *
     * @return Siempre retorna true
     */
    @Override
    public boolean tomarTurno() {
        mostrarTomarDecision();
        for (Carta carta : mazo) {
            if (juego.jugarCarta(carta)) {
                mazo.remove(carta);
                System.out.println(getNombre() + " ha jugado la carta: " + carta.getColor() + carta.getTipo());
                esperar(1500);
                return true;
            }
        }
        System.out.println(getNombre() + " no tiene cartas para jugar");
        esperar(1500);

        ArrayList<Carta> cartasAgregadas = juego.darCartas();
        if (cartasAgregadas.size() == 1) {
            if (juego.jugarCarta(cartasAgregadas.get(0))) {
                Carta cartaTomada = cartasAgregadas.get(0);
                mazo.remove(cartaTomada);
                System.out.println(getNombre() + " tomo una carta, y la pudo jugar, la carta fue: " + cartaTomada.getColor() + cartaTomada.getTipo());
                esperar(1500);
                return true;
            }
            System.out.println(getNombre() + " no pudo jugar la carta que tomo");
            esperar(1500);
        }
        return true;
    }

    /**
     * El computador canta Uno
     *
     * @return true
     */
    @Override
    public boolean cantarUno() {
        System.out.println("El COMPUTADOR CANTO UNO!");
        return true;
    }

    private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
