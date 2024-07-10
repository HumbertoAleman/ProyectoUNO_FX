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

    /**
     * Retorna la instancia del singleton Juego, y si no existe, la crea
     *
     * @return Retorna la unica instancia
     */
    public static Juego getInstance() {
        if (instance == null) instance = new Juego();
        return instance;
    }

    /**
     * Destruye la instancia juego
     */
    public static void destruirInstancia() {
        if (instance != null) instance = null;
    }

    private int cartasATomar;

    private boolean saltarTurno;

    private Jugadores listaJugadores;

    private PilaTomar pilaTomar;

    private PilaJugar pilaJugar;

    private ScoreManager scoreManager;

    /**
     * Revierte el orden de jugadores
     */
    public void revertirOrden() {
        assert (listaJugadores != null);
        if (listaJugadores.getSize() < 3) {
            saltarTurno = true;
            return;
        }
        listaJugadores.cambiarOrden();
    }

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

    /**
     * Metodo para guardar el juego
     */
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

    /**
     * Metodo para cargar el juego
     */
    public void cargarJuego() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setCargador(new CargadorJSONSimple());
        try {
            manejadorSesion.cargarJuego(this);
        } catch (Exception e) {
            System.err.println("Unable to load game");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para guardar las puntuaciones
     */
    public void guardarScores() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setGuardador(new GuardadorGson());
        try {
            manejadorSesion.guardarPuntuacion(scoreManager.getScores());
        } catch (Exception e) {
            System.err.println("Unable to save scores");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar las puntuaciones
     */
    public void cargarScores() {
        ManejadorSesion manejadorSesion = ManejadorSesion.getInstance();
        manejadorSesion.setCargador(new CargadorJSONSimple());
        try {
            manejadorSesion.cargarScores(this);
        } catch (Exception e) {
            System.err.println("Unable to load scores");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para conseguir al jugador actual
     *
     * @return Retorna al jugador actual
     */
    public Jugador getCurrentPlayer() {
        return listaJugadores.getCurrentPlayer();
    }

    /**
     * Metodo para saber si el jugador actual es Humano
     *
     * @return Retorna true si el jugador es Humano
     */
    public boolean isCurrentPlayerHuman() {
        return getCurrentPlayer() instanceof Humano;
    }

    /**
     * Metodo para retornar la carta tope de la pila jugar
     *
     * @return Retorna la carta tope de la pila jugar
     */
    public Carta getTopCard() {
        return pilaJugar.getCartaTope();
    }

    /**
     * Metodo para avanzar al turno del siguiente jugador
     */
    public void siguienteJugador() {
        if (saltarTurno) listaJugadores.siguienteJugador();
        saltarTurno = false;
        listaJugadores.siguienteJugador();
    }

    /**
     * Metodo para que el jugador actual tome su turno
     */
    public void currentPlayerTakeTurn() {
        listaJugadores.jugadorActualTurno();
    }

    /**
     * Metodo para registrar una puntuacion ganadora
     *
     * @param win Si el jugador que esta actualmente jugando gano la partida o no
     */
    public void registerWinnerScore(boolean win) {
        scoreManager.addScore(new Score(win, getCurrentPlayer(), getWinnerScore()));
        guardarScores();
    }

    /**
     * Retorna el puntaje del jugador ganador
     *
     * @return Retorna un integer, que sera el puntaje del jugador ganador
     */
    public int getWinnerScore() {
        LinkedList<Jugador> players = new LinkedList<>(getListaJugadores());
        players.remove(getCurrentPlayer());
        ArrayList<Carta> remainingCards = new ArrayList<>();
        for (Jugador player : players)
            remainingCards.addAll(player.getMazo());

        int score = 0;
        for (Carta carta : remainingCards)
            score += carta.getScore();

        return score;
    }

    /* GETTERS Y SETTERS */

    /**
     * Retorna la cantidad de cartas a tomar
     *
     * @return Retorna la cantidad de cartas a tomar
     */
    public int getCartasATomar() {
        return this.cartasATomar;
    }

    /**
     * Incrementa la cantidad de cartas a tomar
     *
     * @param increase Cantidad de cartas a tomar a incrementar
     */
    public void increaseCartasATomar(int increase) {
        cartasATomar += increase;
    }

    /**
     * Retorna si se salta el turno del siguiente jugador
     *
     * @return Retorna verdadero o falso si el turno se debe saltar
     */
    public boolean isSaltarTurno() {
        return saltarTurno;
    }

    /**
     * Asigna el valor de verdad a saltar turno del siguiente jugador
     *
     * @param saltarTurno Valor de verdad de saltar turno
     */
    public void setSaltarTurno(boolean saltarTurno) {
        this.saltarTurno = saltarTurno;
    }

    /**
     * Retorna una lista con los jugadores del juego
     *
     * @return Retorna una lista con los jugadores
     */
    public List<Jugador> getListaJugadores() {
        return listaJugadores.getListaJugadores();
    }

    /**
     * Asigna una nueva lista de jugadores al juego
     *
     * @param listaJugadores Lista de jugadores a asignar
     */
    public void setListaJugadores(Jugadores listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    /**
     * Retorna la pila de cartas a tomar del juego
     *
     * @return Retorna la pila de cartas a tomar
     */
    public PilaTomar getPilaTomar() {
        return pilaTomar;
    }

    /**
     * Asigna la pila de cartas a tomar
     *
     * @param pilaTomar Pila de cartas a tomar a asignar
     */
    public void setPilaTomar(PilaTomar pilaTomar) {
        this.pilaTomar = pilaTomar;
    }

    /**
     * Retorna la pila de cartas que han sido jugadas en el juego
     *
     * @return Retorna la pila de cartas jugadas
     */
    public PilaJugar getPilaJugar() {
        return pilaJugar;
    }

    /**
     * Asigna una nueva pila de cartas jugadas en el juego
     *
     * @param pilaJugar Pila de cartas a asignar
     */
    public void setPilaJugar(PilaJugar pilaJugar) {
        this.pilaJugar = pilaJugar;
    }

    /**
     * Retorna el manejador de puntuaciones del juego
     *
     * @return Retorna el manejador de puntuaciones
     */
    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    /**
     * Asigna un nuevo manejador de puntuaciones al juego
     *
     * @param scoreManager Manejador de puntuaciones a asignar
     */
    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

}