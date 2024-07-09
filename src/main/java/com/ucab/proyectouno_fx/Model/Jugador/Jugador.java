package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;

import java.util.LinkedList;
import java.util.List;

public abstract class Jugador {
    /**
     * Constructor de Jugador
     *
     * @param nombre nombre jugador
     */
    public Jugador(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    /**
     * Mazo de cartas que le pertenece a los jugadores
     */
    protected final List<Carta> mazo = new LinkedList<>();

    /**
     * Nombre del jugador
     */
    protected final String nombre;

    /**
     * Tipo de jugador (Es utilizado por la libreria GSON)
     */
    protected final String tipo;

    /**
     * Toma el turno del jugador
     */
    public abstract void tomarTurno();

    /**
     * Agrega una carta al mazo del jugador
     *
     * @param carta una carta
     */
    public void agregarCarta(Carta carta) {
        if (carta == null) return;
        mazo.add(carta);
    }

    /**
     * Remueve una carta del mazo del jugador
     *
     * @param card una carta
     */
    public void removeCard(Carta card) {
        mazo.remove(card);
    }

    /**
     * Obtiene el nombre del jugador
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que retorna el mazo de cartas del jugador
     *
     * @return Retorna el mazo del jugador
     */
    public List<Carta> getMazo() {
        return this.mazo;
    }

    /**
     * Obtiene la cantidad de cartas del mazo del jugador
     *
     * @return cantidad de cartas del mazo
     */
    public int getCantidadDeCartas() {
        return mazo.size();
    }
}