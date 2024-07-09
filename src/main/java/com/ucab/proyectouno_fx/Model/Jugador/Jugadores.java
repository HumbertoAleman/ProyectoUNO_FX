package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Controller.GameScreens.MainMenuController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class Jugadores {
    private int index = 0;

    private boolean order = true;

    private final List<Jugador> listaJugadores = new ArrayList<>();

    /**
     * Cambia el turno de los jugaores
     */
    public void cambiarOrden() {
        this.order = !order;
    }

    /**
     * Instancia a los jugadores en una lista
     */
    public void instanciarJugadores() {
        listaJugadores.add(new Humano(MainMenuController.getActiveUser()));
        listaJugadores.add(new Computador("CPU"));
    }

    /**
     * El jugador actual toma turno
     */
    public void jugadorActualTurno() {
        listaJugadores.get(index).tomarTurno();
    }

    /**
     * Cambia el turno al siguiente jugador
     */
    public void siguienteJugador() {
        if (order) {
            index = (index + 1) % listaJugadores.size();
            return;
        }
        index = (index - 1) % listaJugadores.size();
    }

    /**
     * Agrega un jugador a la lista de jugadores
     *
     * @param jugador un jugador
     */
    public void agregarJugador(Jugador jugador) {
        listaJugadores.add(jugador);
    }

    /**
     * Remueve una carta del mazo del jugadora actual
     *
     * @param card Carta {@code }a remover
     */
    public void removeCardFromCurrentPlayer(Carta card) {
        listaJugadores.get(index).removeCard(card);
    }

    /**
     * Asigna a quien le toca el turno
     *
     * @param index posicion del turno del personaje actual
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Obtiene la lista de jugadores
     *
     * @return lista de jugadores
     */
    public List<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    /**
     * Obtiene la catidad de jugadores en la partida
     *
     * @return cantidad de jugadores
     */
    public int getSize() {
        return listaJugadores.size();
    }

    /**
     * Obtiene el jugador actual
     *
     * @return Jugador actual
     */
    public Jugador getCurrentPlayer() {
        return listaJugadores.get(index);
    }
}
