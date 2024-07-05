package com.ucab.proyectouno_fx.controller;

import com.ucab.proyectouno_fx.controller.decks.ActiveDecks;
import com.ucab.proyectouno_fx.controller.decks.CardButton;
import com.ucab.proyectouno_fx.controller.decks.DeckController;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController extends ControllerParent {

    private Juego juego = Juego.getInstance();

    private ActiveDecks decks = new ActiveDecks(this);

    private boolean pickingColor = false;

    private CartaComodin comodinBeingPlayed = null;

    public void refreshDecks() {
        decks.refreshDecks();
    }

    public void refreshPlayPile() {
        playPile.setText(juego.getTopCard().getEtiqueta());
    }

    private List<VBox> getListOfPlayerContainers() {
        return List.of(playerOneContainer, playerTwoContainer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize game, then get player list, and create HBOXES and LABELS for them
        juego.initializeGame();
        decks.initializeDecks(juego.getPlayers(), getListOfPlayerContainers());
        decks.refreshDecks();

        takePile.setText("UNO");
        takePile.setOnAction(event -> {
            // Le damos las cartas al jugador actual
            juego.darCartas();

            // Siguiente jugador ensues
            juego.siguienteJugador();

            // Triggereamos el turno del CPU
            triggerCurrentPlayerTurn();
        });
        playPile.setText(juego.getTopCard().getEtiqueta());
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

    public void enableColorChooser() {
        for (Node child : colorPickContainer.getChildren())
            child.setDisable(false);
    }

    @FXML
    private void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }

    public void triggerCurrentPlayerTurn() {
        if (juego.isCurrentPlayerHuman()) return;

        juego.currentPlayerTakeTurn();
        juego.siguienteJugador();
        juego.getTopCard().ejecutarAccion();

        refreshDecks();
        refreshPlayPile();
    }

    public void triggerChooseColor(Carta card) {
        pickingColor = true;
        for (Node child : colorPickContainer.getChildren())
            child.setDisable(false);
        comodinBeingPlayed = (CartaComodin) card;
    }

    private void triggerColorSelection(char color) {
        assert (pickingColor);
        assert (comodinBeingPlayed != null);

        juego.jugarCarta(comodinBeingPlayed);
        comodinBeingPlayed.setColorSeleccionado(color);

        comodinBeingPlayed = null;
        pickingColor = false;

        refreshDecks();
        refreshPlayPile();

        juego.siguienteJugador();
        triggerCurrentPlayerTurn();

        for (Node child : colorPickContainer.getChildren())
            child.setDisable(true);
    }

    @FXML
    private void triggerRedSelection(ActionEvent event) {
        triggerColorSelection('R');
    }

    @FXML
    private void triggerBlueSelection(ActionEvent event) {
        triggerColorSelection('B');
    }

    @FXML
    private void triggerGreenSelection(ActionEvent event) {
        triggerColorSelection('G');
    }

    @FXML
    private void triggerYellowSelection(ActionEvent event) {
        triggerColorSelection('Y');
    }
}
