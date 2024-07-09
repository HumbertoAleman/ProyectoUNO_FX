package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.ArrayList;

public class Computador extends Jugador {
    /**
     * Constructor de la clase Computador
     *
     * @param nombre nombre del CPU
     */
    public Computador(String nombre) {
        super(nombre, "C");
    }

    /**
     * El computador realiza una accion en su turno
     */
    @Override
    public void tomarTurno() {
        Juego juego = Juego.getInstance();
        for (Carta carta : mazo) {
            if (!juego.jugarCarta(carta)) continue;
            System.out.println(nombre + " jugo " + carta.getEtiqueta());
            mazo.remove(carta);
            return;
        }

        ArrayList<Carta> cartasAgregadas = juego.darCartasAJugadorActual();
        if (cartasAgregadas.size() != 1) return;

        if (juego.jugarCarta(cartasAgregadas.getFirst())) {
            Carta cartaTomada = cartasAgregadas.getFirst();
            mazo.remove(cartaTomada);
            System.out.println("Oponente tomo la carta " + cartaTomada.getEtiqueta());
            return;
        }

        System.out.println(nombre + " no jugo la carta tomada");
    }
}
