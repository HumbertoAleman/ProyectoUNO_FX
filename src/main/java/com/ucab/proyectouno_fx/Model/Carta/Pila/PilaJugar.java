package com.ucab.proyectouno_fx.Model.Carta.Pila;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PilaJugar {
    private final Stack<Carta> listaCartas = new Stack<>();

    /**
     * Coloca una carta en la pila de jugar
     *
     * @param carta una carta
     */
    public void jugarCarta(Carta carta) {
        listaCartas.push(carta);
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
        for (Carta carta : cartasPorDebajo) {
            if (carta instanceof CartaComodin)
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
    }

    /**
     * Toma la primera carta de la pila
     *
     * @return La primera carta de la pila
     */
    public Carta getCartaTope() {
        return listaCartas.peek();
    }
}
