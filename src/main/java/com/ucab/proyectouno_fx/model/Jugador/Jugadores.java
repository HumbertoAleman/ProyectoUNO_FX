package com.ucab.proyectouno_fx.model.Jugador;

import com.ucab.proyectouno_fx.controller.MainMenuController;
import com.ucab.proyectouno_fx.model.Carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class Jugadores {
    private final List<Jugador> listaJugadores = new ArrayList<>();

    /**
     * Constructor jugadores
     */
    public Jugadores() { }

    /**
     * Obtiene la lista de jugadores
     * 
     * @return lista de jugadores
     */
    public List<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    private int index = 0;

    private boolean order = true;

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
     *
     * @return Retorna si el jugador va a salir al menu principal
     */
    public boolean jugadorActualTurno() {
        return listaJugadores.get(index).tomarTurno();
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
     * Obtiene el jugador actual
     *
     * @return Jugador actual
     */
    public Jugador getCurrentPlayer(){
        return listaJugadores.get(index);
    }

    /**
     * Obtiene la cantidad de cartas del mazo del jugador actual
     * 
     * @return cantidad de cartas 
     */
    public int getNumCartasJugadorActual() {
        return listaJugadores.get(index).getCantidadDeCartas();
    }
    /**
     * Obtiene la catidad de jugadores en la partida
     * 
     * @return cantidad de jugadores
     */
    public int size() {
        return listaJugadores.size();
    }

    //Esta funcion es unicamente usada al cargar
    /**
     * Agrega un jugador a la lista de jugadores
     * 
     * @param jugador un jugador
     */
    public void agregarJugador(Jugador jugador){
        listaJugadores.add(jugador);
    }

    /**
     * Asigna a quien le toca el turno
     * 
     * @param index posicion del turno del personaje actual
     */
    public void setIndex(int index){
        this.index = index;
    }

    public void removeCardFromCurrentPlayer(Carta card) {
        listaJugadores.get(index).removeCardFromDeck(card);
    }
}
