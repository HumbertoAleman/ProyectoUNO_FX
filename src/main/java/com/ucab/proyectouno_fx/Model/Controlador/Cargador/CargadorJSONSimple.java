package com.ucab.proyectouno_fx.Model.Controlador.Cargador;

import com.ucab.proyectouno_fx.Model.Carta.Accion.CartaMasDos;
import com.ucab.proyectouno_fx.Model.Carta.Accion.CartaRevertir;
import com.ucab.proyectouno_fx.Model.Carta.Accion.CartaSaltar;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.CartaNumerica;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaCambiarColor;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaMasCuatro;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Controlador.Score.ScoreManager;
import com.ucab.proyectouno_fx.Model.Jugador.Computador;
import com.ucab.proyectouno_fx.Model.Jugador.Humano;
import com.ucab.proyectouno_fx.Model.Jugador.Jugador;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CargadorJSONSimple implements Cargador {
    private String playerDataPath;

    /**
     * Funcion para cargar el juego
     *
     * @throws IOException    Se lanza si ocurre un error al leer el archivo
     * @throws ParseException Se lanza si ocurre un error al transformar el archivo en un json
     */
    @Override
    public void cargarJuego(Juego juego, String playerDataPath) throws IOException, ParseException {
        this.playerDataPath = playerDataPath + "/";
        juego.setListaJugadores(cargarJugadores());
        juego.setPilaJugar(cargarPilaJugar());
        juego.setPilaTomar(cargarPilaTomar(juego.getPilaJugar()));
        juego.setSaltarTurno(cargarSaltarTurno());
        juego.increaseCartasATomar(cargarCartasAtomar());
    }

    @Override
    public void cargarScores(Juego juego, String playerDataPath) throws IOException, ParseException {
        this.playerDataPath = playerDataPath + "/";
        juego.setScoreManager(new ScoreManager(cargarScores()));
    }

    /**
     * Transforma un string del camino al archivo .json en un JSONObject para lectura
     *
     * @param path La direccion del archivo, sea relativa o absoluta
     * @return El JSONObject del json del archivo
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private JSONObject fromPathToJSONObject(String path) throws IOException, ParseException {
        return (JSONObject) new JSONParser().parse(new FileReader(path));
    }

    private JSONArray fromPathToJSONArray(String path) throws IOException, ParseException {
        return (JSONArray) new JSONParser().parse(new FileReader(path));
    }

    /**
     * Transforma un objeto de tipo JSONObject en una Carta
     *
     * @param carta JSONObject a transformar en una carta
     * @return Carta transformada en JSON, si no se puede determinar el tipo de carta, retorna null
     */
    private Carta fromJSONObjectToCarta(JSONObject carta) {
        String numero;
        String tipo;
        char color;

        if (carta.containsKey("tipo") && carta.containsKey("colorSeleccionado")) {
            tipo = carta.get("tipo").toString();
            color = carta.get("colorSeleccionado").toString().charAt(0);
            return tipo.equals("T4") ? new CartaMasCuatro(color) : new CartaCambiarColor(color);
        }

        if (carta.containsKey("color") && carta.containsKey("numero")) {
            numero = carta.get("numero").toString();
            color = carta.get("color").toString().charAt(0);
            return new CartaNumerica(color, numero);
        }

        tipo = carta.get("tipo").toString();
        color = carta.get("color").toString().charAt(0);
        return switch (tipo) {
            case "R" -> new CartaRevertir(color);
            case "T2" -> new CartaMasDos(color);
            case "S" -> new CartaSaltar(color);
            default -> null;
        };
    }

    /**
     * Metodo encargado de cargar los jugadores
     *
     * @return Retorna la lista de jugadores cargada
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private Jugadores cargarJugadores() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject(playerDataPath + "listaJugadores.json");
        JSONArray listaJugadoresJson = (JSONArray) objeto.get("listaJugadores");

        Jugadores listaJugadores = new Jugadores();
        listaJugadores.setIndex(objeto.containsKey("index") ? Integer.parseInt(objeto.get("index").toString()) : 0);

        for (Object jugadorObject : listaJugadoresJson) {
            JSONObject jugadorJSON = (JSONObject) jugadorObject;
            String nombreJugador = jugadorJSON.containsKey("nombre") ? jugadorJSON.get("nombre").toString() : "Jugador";
            Jugador jugador = jugadorJSON.containsKey("tipo") && jugadorJSON.get("tipo").equals("H") ? new Humano(nombreJugador) : new Computador("CPU");

            JSONArray mazoArray = (JSONArray) jugadorJSON.get("mazo");
            for (Object cartaObject : mazoArray)
                jugador.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaObject));

            listaJugadores.agregarJugador(jugador);
        }
        return listaJugadores;
    }

    /**
     * Metodo encargado de cargar la Pila Tomar
     *
     * @return Retorna la Pila Tomar con las cartas que contenga
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private PilaTomar cargarPilaTomar(PilaJugar pilaJugar) throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject(playerDataPath + "pilaTomar.json");
        JSONArray listaCartas = (JSONArray) objeto.get("listaCartas");

        PilaTomar pilaTomar = new PilaTomar(pilaJugar);
        for (Object cartaActual : listaCartas)
            pilaTomar.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaActual));

        return pilaTomar;
    }

    /**
     * Metodo encargado de cargar la PilaJugar
     *
     * @return Retorna la Pila Jugar con las cartas que contenga
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private PilaJugar cargarPilaJugar() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject(playerDataPath + "pilaJugar.json");
        JSONArray listaCartas = (JSONArray) objeto.get("listaCartas");

        PilaJugar pilaJugar = new PilaJugar();
        for (Object cartaActual : listaCartas)
            pilaJugar.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaActual));

        return pilaJugar;
    }

    /**
     * Metodo encargado de cargar si se esta saltando un turno
     *
     * @return Retorna un booleano que dictara si se salta el turno del jugador actual
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private boolean cargarSaltarTurno() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject(playerDataPath + "juego.json");
        return (boolean) objeto.get("saltarTurno");
    }

    /**
     * Metodo encargado de cargar las cartas que tiene que tomar el siguiente jugador
     *
     * @return Retorna un entero con la cantidad de cartas que debera tomar el jugador actual
     * @throws IOException    Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    private int cargarCartasAtomar() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject(playerDataPath + "juego.json");
        return (int) (long) objeto.get("cartasATomar");
    }

    private List<Score> cargarScores() throws IOException, ParseException {
        JSONArray object = fromPathToJSONArray(playerDataPath + "scores.json");

        List<Score> scoreList = new LinkedList<>();
        for (Object score : object)
            scoreList.add(getScoreFromJSONObject((JSONObject) score));
        return scoreList;
    }

    private Score getScoreFromJSONObject(JSONObject object) {
        Jugador jugador = null;
        if (object.containsKey("jugadorGanador")) {
            JSONObject innerObject = (JSONObject) object.get("jugadorGanador");
            if (innerObject.get("tipo").toString().charAt(0) == 'H') {
                jugador = new Humano((String) innerObject.get("nombre"));
            }

            if (innerObject.get("tipo").toString().charAt(0) == 'C') {
                jugador = new Computador((String) innerObject.get("nombre"));
            }
        }
        assert jugador != null;

        int score = 0;
        if (object.containsKey("score")) {
            score = (int) (long) object.get("score");
        }

        boolean win = false;
        if (object.containsKey("win")) {
            win = (boolean) object.get("win");
        }

        return new Score(win, jugador, score);
    }
}