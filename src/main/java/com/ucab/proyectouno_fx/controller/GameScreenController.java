package com.ucab.proyectouno_fx.controller;

import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreenController extends ControllerParent {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer.setText(MainMenuController.getActiveUser());
        Juego.iniciarJuego();
        for (Carta carta : Juego.getCartasHumano())
            addCardToPlayerDeck(carta, true);
        for (Carta carta : Juego.getCartasComputador())
            addCardToOponentDeck(carta, false);

        takePile.setText("UNO");
        takePile.setOnAction(event -> {
            Juego.tomarAccionJugadorActual("T");
            if (Juego.jugadorEsHumano()) refreshPlayerDeck();
            else refreshOponentDeck();
            nextPlayerTurn();
        });
        playPile.setText(Juego.getCartaTope().getEtiqueta());
    }

    private boolean playerTurn = true;

    public void nextPlayerTurn() {
        setPlayerTurn(!getPlayerTurn());
        Juego.siguienteJugador();

        if (Juego.jugadorActualEsCPU()) {
            Juego.tomarAccionJugadorActual();
            playPile.setText(Juego.getCartaTope().getEtiqueta());
            setPlayerTurn(!getPlayerTurn());
            refreshOponentDeck();
            Juego.siguienteJugador();
        }
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
        playerDeck.setDisable(!playerTurn);
        takePile.setDisable(!playerTurn);
    }

    public boolean getPlayerTurn() {
        return this.playerTurn;
    }

    @FXML
    public HBox playerDeck;

    @FXML
    public Label playerActions;

    @FXML
    public HBox oponentDeck;

    @FXML
    public Label oponentActions;

    @FXML
    public Button playPile;

    @FXML
    public Button takePile;

    private Button createCard(Carta carta, boolean mostrar) {
        Button button = new Button(mostrar ? carta.getEtiqueta() : "UNO");
        button.setDisable(!mostrar);
        button.setOnAction(event -> {
            Juego.tomarAccionJugadorActual(carta.getEtiqueta());
            if (!Juego.getCartasJugadorActual().contains(carta)) {
                if (Juego.jugadorActualEsCPU()) removeCardFromOponentDeck(button);
                else removeCardFromPlayerDeck(button);
                playPile.setText(carta.getEtiqueta());
                nextPlayerTurn();
            }
        });
        return button;
    }

    private void addCardToOponentDeck(Carta carta, boolean mostrar) {
        oponentDeck.getChildren().add(createCard(carta, mostrar));
    }

    private void removeCardFromOponentDeck(Button button) {
        oponentDeck.getChildren().remove(button);
    }

    private void refreshOponentDeck() {
        clearOponentDeck();
        for (Carta carta : Juego.getCartasComputador())
            addCardToOponentDeck(carta, false);
    }

    private void clearOponentDeck() {
        oponentDeck.getChildren().clear();
    }

    private void addCardToPlayerDeck(Carta carta, boolean mostrar) {
        playerDeck.getChildren().add(createCard(carta, mostrar));
    }

    private void removeCardFromPlayerDeck(Button button) {
        playerDeck.getChildren().remove(button);
    }

    private void refreshPlayerDeck() {
        clearPlayerDeck();
        for (Carta carta : Juego.getCartasHumano())
            addCardToPlayerDeck(carta, true);
    }

    private void clearPlayerDeck() {
        playerDeck.getChildren().clear();
    }

    @FXML
    public Label currentPlayer;

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }
}
