package com.ucab.proyectouno_fx.Model.Controlador;

import com.ucab.proyectouno_fx.Model.Carta.Accion.CartaAccion;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Carta.Validator;
import com.ucab.proyectouno_fx.Model.Controlador.Cargador.CargadorJSONSimple;
import com.ucab.proyectouno_fx.Model.Controlador.Guardador.GuardadorGson;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Controlador.Score.ScoreManager;
import com.ucab.proyectouno_fx.Model.Jugador.Humano;
import com.ucab.proyectouno_fx.Model.Jugador.Jugador;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Juego {
    private Juego() {
    }

    private static Juego instance;

    public static Juego getInstance() {
        if (instance == null) instance = new Juego();
        return instance;
    }

    private ScoreManager scoreManager;

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public List<Jugador> getPlayers() {
        return listaJugadores.getListaJugadores();
    }

    private int cartasATomar;

    private boolean saltarTurno;

    /**
     * Obtiene las cartas a tomar
     *
     * @return cantidad de cartas
     */
    public int getCartasATomar() {
        return this.cartasATomar;
    }

    /**
     * Aumenta la cantidad de cartas a tomar
     *
     * @param increase Numero de cartas a tomar por aumentar
     */
    public void increaseCartasATomar(int increase) {
        cartasATomar += increase;
    }

    /**
     * Revisa si se debe saltar un turno
     *
     * @return true si hay que saltar un turno, false si no
     */
    public boolean isSaltarTurno() {
        return saltarTurno;
    }

    /**
     * Notifica si se debe de saltar el turno
     *
     * @param saltarTurno true si se debe saltar turno, false si no
     */
    public void setSaltarTurno(boolean saltarTurno) {
        this.saltarTurno = saltarTurno;
    }

    /**
     * Revierte el orden de jugadores
     */
    public void revertirOrden() {
        assert (listaJugadores != null);
        if (listaJugadores.size() < 3) {
            saltarTurno = true;
            return;
        }
        listaJugadores.cambiarOrden();
    }

    private Jugadores listaJugadores;

    private PilaTomar pilaTomar;

    private PilaJugar pilaJugar;

    /**
     * Le da cartas a el jugador actual
     *
     * @return Retorna la lista de las cartas que se le dieron al jugador
     */
    public ArrayList<Carta> darCartasAJugadorActual() {
        ArrayList<Carta> listaRetornar = pilaTomar.tomarCartas(listaJugadores.getCurrentPlayer(), cartasATomar == 0 ? 1 : cartasATomar);
        cartasATomar = 0;
        return listaRetornar;
    }

    /**
     * Revisa si la carta se puede jugar
     *
     * @param carta una carta
     * @return true si se puede jugar la carta, false si no
     */
    public boolean jugarCarta(Carta carta) {
        if (!Validator.validateCard(carta)) return false;
        pilaJugar.jugarCarta(carta);
        listaJugadores.removeCardFromCurrentPlayer(carta);
        return true;
    }

    /**
     * Instancia los objetos del juego
     */
    public void initializeGame() {
        listaJugadores = new Jugadores();
        pilaJugar = new PilaJugar();
        pilaTomar = new PilaTomar(pilaJugar);
        cartasATomar = 0;
        saltarTurno = false;

        pilaTomar.crearListaCartas();

        listaJugadores.instanciarJugadores();
        pilaTomar.repartirCartas(listaJugadores.getListaJugadores());

        Carta primeraCarta = null;
        while (primeraCarta == null || primeraCarta instanceof CartaComodin || primeraCarta instanceof CartaAccion) {
            primeraCarta = pilaTomar.tomarCarta();
            pilaJugar.jugarCarta(primeraCarta);
        }
    }

    public void guardarJuego() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setGuardador(new GuardadorGson());
        try {
            manejadorSesion.guardarJuego(listaJugadores, pilaJugar, pilaTomar, saltarTurno, cartasATomar);
        } catch (Exception e) {
            System.err.println("Unable to save game");
            System.err.println(e.getMessage());
        }
    }

    public void cargarJuego() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setCargador(new CargadorJSONSimple());
        try {
            manejadorSesion.cargarJuego(this);
        } catch (Exception e) {
            System.err.println("Unable to load game");
            System.err.println(e.getMessage());
        }
    }

    public void cargarScores() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setCargador(new CargadorJSONSimple());
        try {
            manejadorSesion.cargarScores(this);
        } catch (Exception e) {
            System.err.println("Unable to load game");
            System.err.println(e.getMessage());
        }
    }

    public Jugador getCurrentPlayer() {
        return listaJugadores.getCurrentPlayer();
    }

    public boolean isCurrentPlayerHuman() {
        return getCurrentPlayer() instanceof Humano;
    }

    public Carta getTopCard() {
        return pilaJugar.getCartaTope();
    }

    public void siguienteJugador() {
        if (saltarTurno) {
            listaJugadores.siguienteJugador();
            saltarTurno = false;
        }

        listaJugadores.siguienteJugador();
    }

    public void currentPlayerTakeTurn() {
        listaJugadores.jugadorActualTurno();
    }

    /**
     * Limpia la patalla
     *
     * @deprecated
     */
    public static void limpiarConsola() {
        // Esto es probablemente lo mas tonto que he hecho en mis 3.5 semestres que he estado en esta universidad
        // pero me da demasiada flojera encontrar una manera de limpiar la consola en java.
        // Si alguien encuentra una manera de limpiar la consola de verdad, y que funcione tanto en mac
        // como en windows se lo agradeceria.
        // - Humberto Aleman

        System.out.println("\n".repeat(15));
    }

    public void setListaJugadores(Jugadores listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public void setPilaJugar(PilaJugar pilaJugar) {
        this.pilaJugar = pilaJugar;
    }

    public PilaJugar getPilaJugar() {
        return pilaJugar;
    }

    public void setPilaTomar(PilaTomar pilaTomar) {
        this.pilaTomar = pilaTomar;
    }

    public void registerWinner(boolean win) {
        scoreManager.addScore(new Score(win, getCurrentPlayer(), getWinnerScore()));

        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setGuardador(new GuardadorGson());
        try {
            manejadorSesion.guardarPuntuacion(scoreManager.getScores());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWinnerScore() {
        LinkedList<Jugador> players = new LinkedList<>(getPlayers());
        players.remove(getCurrentPlayer());
        ArrayList<Carta> remainingCards = new ArrayList<>();
        for (Jugador player : players)
            remainingCards.addAll(player.getMazo());

        int score = 0;
        for (Carta carta : remainingCards)
            score += carta.getScore();

        return score;
    }
}