package com.ucab.proyectouno_fx.model.Carta.Pila;

import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import com.ucab.proyectouno_fx.model.Jugador.ImpresoraCarta;

import java.util.*;

public class PilaJugar {
    private final Stack<Carta> listaCartas = new Stack<>();
    private boolean cartaFueJugada = false;

    /**
     * Coloca una carta en la pila de jugar
     *
     * @param carta una carta
     */
    public void jugarCarta(Carta carta) {
        listaCartas.push(carta);
        cartaFueJugada = true;
    }

    /**
     * Ejecuta la accion de la carta jugada
     */
    public void usarEfectoDeCarta() {
        if (!cartaFueJugada) return;
        cartaFueJugada = false;
        listaCartas.peek().ejecutarAccion();
    }

    /**
     * Muestra la carta en el tope de la pila de jugar
     */
    public void mostrarCartaTope() {
        System.out.println("+---+");
        ImpresoraCarta.imprimirCuerpoCarta(listaCartas.peek(), true);
        System.out.println();
        System.out.println("+---+");
    }

    /**
     * Pasa todas las cartas de la pila de jugar a la pila de jugar, menos el primer elemento, y la vacia
     *
     * @return Lista de cartas
     */
    public List<Carta> getCartasPorDebajo() {
        Carta primeraCarta = listaCartas.pop();
        LinkedList<Carta> cartasPorDebajo = new LinkedList<>(listaCartas);
        listaCartas.clear();
        listaCartas.add(primeraCarta);
        for(Carta carta : cartasPorDebajo){
            if(carta instanceof CartaComodin)
                ((CartaComodin) carta).setColorSeleccionado('C');
        }
        return cartasPorDebajo;
    }

    /**
     * Agrega una carta a la pila
     *
     * @param carta carta a agregar
     */
    public void agregarCarta(Carta carta) {
        listaCartas.add(carta);
        cartaFueJugada = true;
    }

    public Carta getCartaTope() {
        return listaCartas.peek();
    }
}
