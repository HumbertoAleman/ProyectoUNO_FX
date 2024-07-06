package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.LinkedList;

public abstract class Jugador {
    protected transient final Juego juego = Juego.getInstance();

    protected LinkedList<Carta> mazo = new LinkedList<>();

    protected final String nombre;

    protected final String tipo;

    public LinkedList<Carta> getMazo() {
        return this.mazo;
    }

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
     * Obtiene el nombre del jugador
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega una carta al mazo
     *
     * @param carta una carta
     */
    public void agregarCarta(Carta carta) {
        if (carta == null) return;
        mazo.add(carta);
    }

    /**
     * Obtiene la cantidad de cartas del mazo del jugador
     *
     * @return cantidad de cartas del mazo
     */
    public int getCantidadDeCartas() {
        return mazo.size();
    }

    /**
     * Obtiene la primera carta del mazo del jugaor
     *
     * @return Carta
     */
    public Carta getCarta() {
        return mazo.getFirst();
    }

    /**
     * Toma el turno del jugador
     *
     * @return Retorna si el jugador tomo la decision de regresar al menu principal
     */
    public abstract boolean tomarTurno();

    public void removeCardFromDeck(Carta card) {
        mazo.remove(card);
    }
}
