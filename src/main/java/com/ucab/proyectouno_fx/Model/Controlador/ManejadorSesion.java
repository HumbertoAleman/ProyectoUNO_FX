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

    public static ManejadorSesion getInstance() {
        if (instance == null) instance = new ManejadorSesion();
        return instance;
    }

    private Guardador guardador;

    private Cargador cargador;

    private File currentPlayerDirectory;

    private final File directory;

    private File getUserDir(String playerName) {
        return new File(directory.getName() + "/" + playerName);
    }

    public boolean userDirExists(String playerName) {
        assert (directory != null);
        return getUserDir(playerName).exists();
    }

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

    public boolean loginPlayerDirectory(String playerName, String playerPassword) {
        assert (directory != null);
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader(directory.getName() + "/" + playerName + "/" + "user_info.json"));
        } catch (Exception e) {
            // TODO: Potencialmente mostrar mensaje de error
            return false;
        }

        if (!jsonObject.containsKey("name") || !jsonObject.containsKey("password")) return false;

        String name = jsonObject.get("name").toString();
        String password = jsonObject.get("password").toString();
        boolean login = name.equals(playerName) && (password.isEmpty() || password.equals(playerPassword));
        if (!login) return false;

        currentPlayerDirectory = new File(directory.getName() + "/" + playerName);
        return true;
    }

    public void setGuardador(Guardador guardador) {
        this.guardador = guardador;
    }

    public void guardarJuego(Jugadores listaJugadores, PilaJugar pilaJugar, PilaTomar pilaTomar, boolean saltarTurno, int cartasATomar) throws IOException {
        guardador.guardarJuego(currentPlayerDirectory.getPath(), listaJugadores, pilaJugar, pilaTomar, saltarTurno, cartasATomar);
    }

    public void guardarPuntuacion(List<Score> scoreList) throws IOException {
        guardador.guardarPuntuacion(currentPlayerDirectory.getPath(), scoreList);
    }


    public void setCargador(Cargador cargador) {
        this.cargador = cargador;
    }

    public void cargarJuego(Juego juego) throws IOException, ParseException {
        cargador.cargarJuego(juego, currentPlayerDirectory.getPath());
    }

    public void cargarScores(Juego juego) throws IOException, ParseException {
        cargador.cargarScores(juego, currentPlayerDirectory.getPath());
    }
}
