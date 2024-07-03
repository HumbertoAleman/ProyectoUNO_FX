package com.ucab.proyectouno_fx.model.Controlador;

import com.ucab.proyectouno_fx.model.Carta.Accion.CartaAccion;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.model.Jugador.Jugador;
import com.ucab.proyectouno_fx.model.Jugador.Jugadores;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Juego {
    private Juego() {
    }

    private static int cartasATomar;
    /**
     * Obtiene las cartas a tomar
     * 
     * @return cantidad de cartas
     */
    public static int getCartasATomar() {
        return cartasATomar;
    }
    /**
     * Asigna la cantidad de cartas a tomar
     *
     * @param cartasATomar cartas a tomar
     */
    public static void setCartasATomar(int cartasATomar) {
        Juego.cartasATomar = cartasATomar;
    }

    private static boolean saltarTurno;
    /**
     * Revisa si se debe saltar un turno
     *
     * @return true si hay que saltar un turno, false si no
     */
    public static boolean isSaltarTurno() {
        return saltarTurno;
    }
    /**
     * Notifica si se debe de saltar el turno
     *
     * @param saltarTurno true si se debe saltar turno, false si no
     */
    public static void setSaltarTurno(boolean saltarTurno) {
        Juego.saltarTurno = saltarTurno;
    }
    /**
     * Revierte el orden de jugadores
     */
    public static void revertirOrden() {
        if (listaJugadores == null) return;
        listaJugadores.cambiarOrden();
    }

    private static Jugadores listaJugadores;
    /**
     * Obtiene la cantidad de jugadores en la partida
     * 
     * @return cantidad de jugadores
     */
    public static int getNumeroJugadores() {
        return listaJugadores == null ? 0 : listaJugadores.size();
    }
    /**
     * Revisa si el jugador actual es humano
     * 
     * @return true si el jugador es humano, false si no
     */
    public static boolean jugadorEsHumano() {
        return listaJugadores != null && listaJugadores.validarJugadorHumano();
    }
    /**
     * Revisa si el jugador actual es CPU
     * 
     * @return true si el jugador es CPU, false si no
     */
    public static boolean jugadorActualEsCPU() {
        return listaJugadores != null && listaJugadores.validarJugadorComputador();
    }

    private static PilaTomar pilaTomar;
    /**
     * Le da cartas a un jugador
     *
     * @param jugador Jugador al que se le daran las cartas
     * @return Retorna la lista de las cartas que se le dieron al jugador
     */
    public static ArrayList<Carta> darCartas(Jugador jugador) {
        ArrayList<Carta> listaRetornar = pilaTomar.tomarCartas(jugador, cartasATomar == 0 ? 1 : cartasATomar);
        cartasATomar = 0;
        return listaRetornar;
    }

    private static PilaJugar pilaJugar;
    /**
     * Revisa si la carta se puede jugar
     *
     * @param carta una carta
     * @return true si se puede jugar la carta, false si no
     */
    public static boolean jugarCarta(Carta carta) {
        if (!pilaJugar.validarCarta(carta)) return false;
        pilaJugar.jugarCarta(carta);
        return true;
    }
    /**
     * Obtiene las cartas de la Pila jugar menos la del tope
     * 
     * @return lista de cartas
     */
    public static List<Carta> getCartasPorDebajo() {
        return pilaJugar.getCartasPorDebajo();
    }
    /**
     * Muestra el menu del juego
     */
    public static void mostrarMenu() {
        System.out.println("----------------------------------");
        System.out.format("| %-30s |%n", "UNO");
        System.out.format("| %30s |%n", "");
        System.out.format("| %4s %25s |%n", "1.", "Comenzar juego");
        System.out.format("| %4s %25s |%n", "2.", "Cargar juego");
        System.out.format("| %30s |%n", "");
        System.out.format("| %4s %25s |%n", "0.", "Salir del juego");
        System.out.println("----------------------------------");
        System.out.println();
        System.out.print("Seleccion: ");
    }

    /**
     * Instancia los objetos del juego
     */
    public static void iniciarJuego() {
        listaJugadores = new Jugadores();
        pilaTomar = new PilaTomar();
        pilaJugar = new PilaJugar();
        cartasATomar = 0;
        saltarTurno = false;

        pilaTomar.crearListaCartas();

        listaJugadores.instanciarJugadores();
        pilaTomar.repartirCartas(listaJugadores.getListaJugadores());

        Carta primeraCarta = null;
        while (primeraCarta == null ||
                primeraCarta instanceof CartaComodin ||
                primeraCarta instanceof CartaAccion) {
            primeraCarta = pilaTomar.tomarCarta();
            pilaJugar.jugarCarta(primeraCarta);
        }
    }

    /**
     * Funcion para cargar el juego
     * @throws IOException Se lanza si ocurre un error al leer el archivo
     * @throws ParseException Se lanza si ocurre un error al transformar el archivo en un json
     */
    public static void cargarJuego() throws IOException, ParseException {
        listaJugadores = Cargador.cargarJugadores();
        pilaTomar = Cargador.cargarPilaTomar();
        pilaJugar = Cargador.cargarPilaJugar();

        saltarTurno = Cargador.cargarSaltarTurno();
        cartasATomar = Cargador.cargarCartasAtomar();
    }

    /**
     * Loop del juego
     * - Se revisa si se salta el turno de un jugador
     * - Se muestra la carta actual
     * - Se guarda el juego
     * - El jugador actual toma su turno
     * - Si solo queda una carta, y es comodin, se le da una carta al jugador
     * - Si solo queda una carta, y no es comodin, el jugador canta UNO
     * - Se aplica el efecto de la carta jugada
     * - Se pasa al siguiente jugador
     * @return Retorna un booleano, retorna falso si el juego termina
     */
    public static boolean loopJuego() {
        // limpiarConsola();
        if (saltarTurno) {
            listaJugadores.siguienteJugador();
            saltarTurno = false;
        }

        System.out.println();
        System.out.println();
        pilaJugar.mostrarCartaTope();
        System.out.println();

        try {
            Guardador.guardarJuego(listaJugadores, pilaJugar, pilaTomar, saltarTurno, cartasATomar);
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.out.println();
            System.out.println("Ha ocurrido un error guardando el juego, continuando...");
            try {
                Thread.sleep(3000);
            } catch (Exception sleepError) {
                System.err.println(sleepError.getMessage());
            }
            // limpiarConsola();
        }
        if (!listaJugadores.jugadorActualTurno())
            return false;
        int cartas = listaJugadores.getNumCartasJugadorActual();

        if (cartas == 1) {
            if (listaJugadores.getJugadorActual().getCarta() instanceof CartaComodin) {
                System.out.println("No se puede ganar con un comodin, estas obligado a tomar una carta...");
                try {
                    Thread.sleep(1500);
                } catch (Exception sleepError) {
                    System.err.println(sleepError.getMessage());
                }
                pilaTomar.tomarCartas(listaJugadores.getJugadorActual(),1);
            } else if(!listaJugadores.getJugadorActual().cantarUno()){
                pilaTomar.tomarCartas(listaJugadores.getJugadorActual(),7);
            }
        } else if (cartas == 0) {
            System.out.println("HA GANADO : " + listaJugadores.getJugadorActual().getNombre());
            // FUNCION GANAR
            return false;
        }

        pilaJugar.usarEfectoDeCarta();
        listaJugadores.siguienteJugador();
        return true;
    }

    public static LinkedList<Carta> getCartasHumano() {
        return listaJugadores.getCartasHumano();
    }

    public static LinkedList<Carta> getCartasComputador() {
        return listaJugadores.getCartasComputador();
    }

    public static LinkedList<Carta> getCartasJugadorActual() {
        return listaJugadores.getCartasJugadorActual();
    }

    public static Carta getCartaTope() {
        return pilaJugar.getCartaTope();
    }

    public static void siguienteJugador() {
        listaJugadores.siguienteJugador();
    }

    public static void tomarAccionJugadorActual() {
        listaJugadores.jugadorActualTurno();
    }

    public static void tomarAccionJugadorActual(String seleccion) {
        listaJugadores.jugadorActualTurno(seleccion);
        pilaJugar.usarEfectoDeCarta();
    }

    /**
     * Limpia la patalla
     * @deprecated
     */
    public static void limpiarConsola() {
        // Esto es probablemente lo mas tonto que he hecho en mis 3.5 semestres que he estado en esta universidad
        // pero me da demasiada flojera encontrar una manera de limpiar la consola en java.
        // Si alguien encuentra una manera de limpiar la consola de verdad, y que funcione tanto en mac
        // como en windows se lo agradeceria.
        // - Humberto Aleman

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 15; i++)
            out.append("\n");
        System.out.println(out);
    }
}