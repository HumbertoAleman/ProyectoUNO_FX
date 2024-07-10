package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controladora de la vista MainMenu
 */
public class MainMenuController extends ControllerParent {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer.setText(activeUser);
    }

    private static String activeUser = "TEST PLAYER";

    /**
     * Metodo que establece el usuario activo
     *
     * @param activeUser Usuario activo
     */
    public static void setActiveUser(String activeUser) {
        MainMenuController.activeUser = activeUser;
    }

    /**
     * Metodo que obtiene el usuario activo
     *
     * @return Usuario activo
     */
    public static String getActiveUser() {
        return MainMenuController.activeUser;
    }

    /**
     * Metodo que obtiene el jugador actual
     */
    @FXML
    private Label currentPlayer;

    /**
     * Metodo que inicia un nuevo juego
     *
     * @param event Evento de la accion
     * @throws IOException Excepcion de entrada y salida
     */
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        Juego.getInstance().initializeGame();
        switchToScene(event, gameScreenView);
    }

    /**
     * Metodo que carga un juego guardado
     *
     * @param event Evento de la accion
     * @throws IOException Excepcion de entrada y salida
     */
    @FXML
    public void loadGame(ActionEvent event) throws IOException {
        try {
            Juego.getInstance().cargarJuego();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        switchToScene(event, gameScreenView);
    }

    /**
     * Metodo que muestra la vista de puntajes
     *
     * @param event Evento de la accion
     * @throws IOException Excepcion de entrada y salida
     */
    @FXML
    public void gotoScoresView(ActionEvent event) throws IOException {
        switchToScene(event, gameScoresView);
    }

    /**
     * Metodo que cierra la aplicacion
     */
    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        activeUser = null;
        switchToScene(event, registerAuthView);
        Juego.destruirInstancia();
    }
}
