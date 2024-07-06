package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;

import java.util.ArrayList;

public class Computador extends Jugador {
    /**
     * Constructor Computador
     *
     * @param nombre nombre del CPU
     */
    public Computador(String nombre) {
        super(nombre, "C");
    }

    /**
     * El computador realiza una accion en su turno
     *
     * @return Siempre retorna true
     */
    @Override
    public boolean tomarTurno() {
        for (Carta carta : mazo) {
            if (!juego.jugarCarta(carta)) continue;
            System.out.println(nombre + " jugo " + carta.getEtiqueta());
            mazo.remove(carta);
            return true;
        }

        ArrayList<Carta> cartasAgregadas = juego.darCartasAJugadorActual();
        if (cartasAgregadas.size() != 1) return true;

        if (juego.jugarCarta(cartasAgregadas.getFirst())) {
            Carta cartaTomada = cartasAgregadas.getFirst();
            mazo.remove(cartaTomada);
            System.out.println("Oponente tomo la carta " + cartaTomada.getEtiqueta());
            return true;
        }

        System.out.println(nombre + " no jugo la carta tomada");
        return true;
    }
}
