package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.CPUControllerActions;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.ColorSelector;
import com.ucab.proyectouno_fx.Controller.GameScreens.Decks.ActiveDecks;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Jugador.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController extends ControllerParent {

    private Juego juego;

    private ActiveDecks activeDecks;

    private ColorSelector colorSelector;

    public boolean isPickingColor() {
        return colorSelector.isPickingColor();
    }

    private CPUControllerActions cpuActions;

    public void triggerCPUTurn() {
        cpuActions.triggerCPUTurn();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.juego = Juego.getInstance();
        this.activeDecks = new ActiveDecks(this);
        this.colorSelector = new ColorSelector(this, colorPickContainer);
        this.cpuActions = new CPUControllerActions(this, colorSelector);

        // Get player list, and create HBOXES and LABELS for them
        activeDecks.initializeDecks(juego.getPlayers(), List.of(playerOneContainer, playerTwoContainer));
        humanPlayersNameLabel.setText(MainMenuController.getActiveUser());

        takePile.setText("UNO");
        takePile.setOnAction(event -> {
            // Le damos las cartas al jugador actual
            juego.darCartasAJugadorActual();

            // Siguiente jugador ensues
            juego.siguienteJugador();

            // Triggereamos el turno del CPU
            cpuActions.triggerCPUTurn();

            refreshAll();
        });

        playPile.setText(juego.getTopCard().getEtiqueta());

        colorSelector.setColorPickerDisable(true);

        refreshAll();
    }

    @FXML
    private VBox playerOneContainer;

    @FXML
    private VBox playerTwoContainer;

    @FXML
    private Button playPile;

    @FXML
    private Button takePile;

    @FXML
    private Label humanPlayersNameLabel;

    @FXML
    private GridPane colorPickContainer;

    @FXML
    private void triggerRedSelection() {
        colorSelector.triggerColorSelection('R');
    }

    @FXML
    private void triggerBlueSelection() {
        colorSelector.triggerColorSelection('B');
    }

    @FXML
    private void triggerGreenSelection() {
        colorSelector.triggerColorSelection('G');
    }

    @FXML
    private void triggerYellowSelection() {
        colorSelector.triggerColorSelection('Y');
    }

    @FXML
    private void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }

    public void triggerChooseColor(Carta card) {
        colorSelector.triggerChooseColor(card);
    }

    public void refreshAll() {
        refreshDecks();
        refreshPlayPile();
    }

    private void refreshDecks() {
        activeDecks.refreshDecks();
    }

    private void refreshPlayPile() {
        playPile.setText(juego.getTopCard().getEtiqueta());
    }

    public void triggerWinEvent() {
        LinkedList<Jugador> players = new LinkedList<>(juego.getPlayers());
        players.remove(juego.getCurrentPlayer());
        ArrayList<Carta> remainingCards = new ArrayList<>();
        for (Jugador player : players)
            remainingCards.addAll(player.getMazo());

        int score = 0;
        for (Carta carta : remainingCards)
            score += carta.getScore();

        System.out.println("Final score: " + score);

        /* TODO
        1- mover esta logica a juego, no deberia estar aqui
        2- hacer que los elementos se desactiven cuando alguien gana, y mostrar un boton para regresar al menu
        3- mostrar que jugador gano cuando termina la partida
        */
    }
}
