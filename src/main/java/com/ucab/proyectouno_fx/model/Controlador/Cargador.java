package com.ucab.proyectouno_fx.model.Controlador;

import com.ucab.proyectouno_fx.model.Carta.Accion.CartaMasDos;
import com.ucab.proyectouno_fx.model.Carta.Accion.CartaRevertir;
import com.ucab.proyectouno_fx.model.Carta.Accion.CartaSaltar;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.CartaNumerica;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaCambiarColor;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaMasCuatro;
import com.ucab.proyectouno_fx.model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.model.Jugador.Computador;
import com.ucab.proyectouno_fx.model.Jugador.Humano;
import com.ucab.proyectouno_fx.model.Jugador.Jugador;
import com.ucab.proyectouno_fx.model.Jugador.Jugadores;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public interface Cargador {
    /**
     * Transforma un string del camino al archivo .json en un JSONObject para lectura
     * @param path La direccion del archivo, sea relativa o absoluta
     * @return El JSONObject del json del archivo
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static JSONObject fromPathToJSONObject(String path) throws IOException, ParseException {
        return (JSONObject) new JSONParser().parse(new FileReader(path));
    }

    /**
     * Transforma un objeto de tipo JSONObject en una Carta
     * @param carta JSONObject a transformar en una carta
     * @return Carta transformada en JSON, si no se puede determinar el tipo de carta, retorna null
     */
    static Carta fromJSONObjectToCarta(JSONObject carta) {
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
        switch (tipo) {
            case "R":
                return new CartaRevertir(color);
            case "T2":
                return new CartaMasDos(color);
            case "S":
                return new CartaSaltar(color);
        }
        return null;
    }

    /**
     * Metodo encargado de cargar los jugadores
     * @return Retorna la lista de jugadores cargada
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static Jugadores cargarJugadores() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject("listaJugadores.json");
        JSONArray listaJugadoresJson = (JSONArray) objeto.get("listaJugadores");

        Jugadores listaJugadores = new Jugadores();
        listaJugadores.setIndex(objeto.containsKey("index") ? Integer.parseInt(objeto.get("index").toString()) : 0);

        for (Object jugadorObject : listaJugadoresJson) {
            JSONObject jugadorJSON = (JSONObject) jugadorObject;
            String nombreJugador = jugadorJSON.containsKey("nombre")
                    ? jugadorJSON.get("nombre").toString()
                    : "Jugador";
            Jugador jugador = jugadorJSON.containsKey("tipo") && jugadorJSON.get("tipo").equals("H")
                    ? new Humano(nombreJugador)
                    : new Computador("CPU");

            JSONArray mazoArray = (JSONArray) jugadorJSON.get("mazo");
            for (Object cartaObject : mazoArray)
                jugador.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaObject));

            listaJugadores.agregarJugador(jugador);
        }
        return listaJugadores;
    }

    /**
     * Metodo encargado de cargar la Pila Tomar
     * @return Retorna la Pila Tomar con las cartas que contenga
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static PilaTomar cargarPilaTomar() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject("pilaTomar.json");
        JSONArray listaCartas = (JSONArray) objeto.get("listaCartas");

        PilaTomar pilaTomar = new PilaTomar();
        for (Object cartaActual : listaCartas)
            pilaTomar.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaActual));

        return pilaTomar;
    }

    /**
     * Metodo encargado de cargar la PilaJugar
     * @return Retorna la Pila Jugar con las cartas que contenga
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static PilaJugar cargarPilaJugar() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject("pilaJugar.json");
        JSONArray listaCartas = (JSONArray) objeto.get("listaCartas");

        PilaJugar pilaJugar = new PilaJugar();
        for (Object cartaActual : listaCartas)
            pilaJugar.agregarCarta(fromJSONObjectToCarta((JSONObject) cartaActual));

        return pilaJugar;
    }

    /**
     * Metodo encargado de cargar si se esta saltando un turno
     * @return Retorna un booleano que dictara si se salta el turno del jugador actual
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static boolean cargarSaltarTurno() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject("juego.json");
        return (boolean) objeto.get("saltarTurno");
    }

    /**
     * Metodo encargado de cargar las cartas que tiene que tomar el siguiente jugador
     * @return Retorna un entero con la cantidad de cartas que debera tomar el jugador actual
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     * @throws ParseException Sera lanzada si hay un error transformando el json en objeto
     */
    static int cargarCartasAtomar() throws IOException, ParseException {
        JSONObject objeto = fromPathToJSONObject("juego.json");
        return (int) (long) objeto.get("cartasATomar");
    }
}