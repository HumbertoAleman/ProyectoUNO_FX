package com.ucab.proyectouno_fx.Model.Controlador;

import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaJugar;
import com.ucab.proyectouno_fx.Model.Carta.Pila.PilaTomar;
import com.ucab.proyectouno_fx.Model.Controlador.Cargador.Cargador;
import com.ucab.proyectouno_fx.Model.Controlador.Guardador.Guardador;
import com.ucab.proyectouno_fx.Model.Controlador.Score.Score;
import com.ucab.proyectouno_fx.Model.Jugador.Jugadores;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class ManejadorSesion {
    private ManejadorSesion() {
        directory = new File("save_data");
        if (!directory.exists()) {
            System.out.println("Creating directory save_data");
            System.out.println("Created?: " + directory.mkdirs());
        }
    }

    private static ManejadorSesion instance;

    /**
     * Retorna la unica instancia del singleton ManejadorSesion, si no existe, la crea
     *
     * @return Retorna la instancia
     */
    public static ManejadorSesion getInstance() {
        if (instance == null) instance = new ManejadorSesion();
        return instance;
    }

    private Guardador guardador;

    private Cargador cargador;

    private File currentPlayerDirectory;

    private final File directory;

    /**
     * Registra un nuevo directorio de un usuario
     *
     * @param playerName Nombre del usuario a registrar
     * @param password   Contraseña del usuario a registrar
     */
    public void registerPlayerDirectory(String playerName, String password) {
        File dirToCreate = getUserDir(playerName);

        System.out.println("Creating directory " + dirToCreate.getPath());
        System.out.println("Created?: " + dirToCreate.mkdirs());

        try {
            FileWriter fileWriter = new FileWriter(dirToCreate + "/" + "user_info.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{ \"name\": \"" + playerName + "\", \"password\": \"" + password + "\" }");
            bufferedWriter.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Hace login en el directorio determinado utilizando la contraseña dada
     *
     * @param playerName     Nombre del usuario
     * @param playerPassword Contraseña del usuario
     * @return Retorna true si el login fue exitoso, retorna false si no lo fue
     */
    public boolean loginPlayerDirectory(String playerName, String playerPassword) {
        assert (directory != null);
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader(directory.getName() + "/" + playerName + "/" + "user_info.json"));
        } catch (Exception e) {
            return false;
        }

        if (!jsonObject.containsKey("name") || !jsonObject.containsKey("password")) return false;

        String name = jsonObject.get("name").toString();
        String password = jsonObject.get("password").toString();
        boolean login = name.equals(playerName) && (password.isEmpty() || password.equals(playerPassword));
        if (!login) return false;

        currentPlayerDirectory = new File(directory.getName() + "/" + playerName);
        File scoresFile = new File(currentPlayerDirectory.getPath() + "/scores.json");
        if (!scoresFile.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(currentPlayerDirectory.getPath() + "/" + "scores.json");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("[]");
                bufferedWriter.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }


        return true;
    }

    /**
     * Metodo para guardar el juego
     *
     * @param listaJugadores Lista de jugadores a guardar
     * @param pilaJugar      Pila de cartas jugadas a guardar
     * @param pilaTomar      Pila de cartas tomadas a guardar
     * @param saltarTurno    Si se salta el turno actual
     * @param cartasATomar   La cantidad de cartas a tomar
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    public void guardarJuego(Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException {
        guardador.guardarJuego(currentPlayerDirectory.getPath(), listaJugadores, pilaJugar, pilaTomar, saltarTurno, cartasATomar);
    }

    /**
     * Metodo para guardar la lista de puntuaciones
     *
     * @param scoreList La lista de puntuaciones a guardar
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    public void guardarPuntuacion(List<Score> scoreList) throws IOException {
        guardador.guardarPuntuacion(currentPlayerDirectory.getPath(), scoreList);
    }

    /**
     * Metodo para cargar el juego
     *
     * @param juego Instancia de juego a donde cargar la informacion
     * @throws IOException    Excepcion a lanzar si ocurre un error de IO
     * @throws ParseException Excepcion a lanzar si ocurre un error con el JSON
     */
    public void cargarJuego(Juego juego) throws IOException, ParseException {
        cargador.cargarJuego(juego, currentPlayerDirectory.getPath());
    }

    /**
     * Metodo para cargar las puntuaciones
     *
     * @param juego Instancia de juego a donde cargar la informacion
     * @throws IOException    Excepcion a lanzar si ocurre un error de IO
     * @throws ParseException Excepcion a lanzar si ocurre un error con el JSON
     */
    public void cargarScores(Juego juego) throws IOException, ParseException {
        cargador.cargarScores(juego, currentPlayerDirectory.getPath());
    }

    /**
     * Metodo que retorna el directorio de un usuario especificado
     *
     * @param playerName Nombre del jugador
     * @return String con el camino al directorio del jugador
     */
    private File getUserDir(String playerName) {
        return new File(directory.getName() + "/" + playerName);
    }

    /**
     * Metodo para revisar si el directorio de un jugador existe
     *
     * @param playerName Nombre del jugador
     * @return Retorna true si existe, false si no existe
     */
    public boolean userDirExists(String playerName) {
        assert (directory != null);
        return getUserDir(playerName).exists();
    }

    /**
     * Metodo para asignar el guardador
     *
     * @param guardador Guardador a asignar
     */
    public void setGuardador(Guardador guardador) {
        this.guardador = guardador;
    }

    /**
     * Metodo para asignar al cargador
     *
     * @param cargador Cargador a asignar
     */
    public void setCargador(Cargador cargador) {
        this.cargador = cargador;
    }
}
