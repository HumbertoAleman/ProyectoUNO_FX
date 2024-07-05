package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;

import java.util.LinkedList;

public abstract class Jugador {
    protected LinkedList<Carta> mazo = new LinkedList<>();

    protected String tipo;

    private final String nombre;

    public LinkedList<Carta> getMazo() {
        return this.mazo;
    }

    /**
     * Constructor de Jugador
     *
     * @param nombre nombre jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
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
     * Revisa si el jugador canta uno
     *
     * @return true si canta uno, false si no
     */
    public abstract boolean cantarUno();

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
