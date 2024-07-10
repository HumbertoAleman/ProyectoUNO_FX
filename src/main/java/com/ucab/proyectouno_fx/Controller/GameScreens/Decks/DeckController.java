package com.ucab.proyectouno_fx.Controller.GameScreens.Decks;

import com.ucab.proyectouno_fx.Controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Carta.Validator;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Jugador.Humano;
import com.ucab.proyectouno_fx.Model.Jugador.Jugador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Clase controladora de la vista de la mano de cartas de un jugador
 */
public class DeckController {
    public DeckController(Jugador jugador, VBox mainContainer, GameScreenController controller) {
        this.jugador = jugador;
        this.isPlayerHuman = jugador instanceof Humano;
        this.actions = new Label();
        this.deck = new HBox();

        mainContainer.getChildren().add(actions);
        mainContainer.getChildren().add(new ScrollPane(deck));

        assert (controller != null);
        this.controller = controller;
    }

    private final GameScreenController controller;

    private final Juego juego = Juego.getInstance();

    private final Jugador jugador;

    private final boolean isPlayerHuman;

    @FXML
    private final HBox deck;

    @FXML
    private final Label actions;

    public void clearHBox() {
        deck.getChildren().clear();
    }

    /**
     * Retorna Verdadero (DESHABILITADO) si al jugador actual no le pertenece este mazo
     *
     * @return Valor de verdad si se debe deshabilitar el mazo
     */
    private boolean getDisabledStatus() {
        return !juego.getCurrentPlayer().equals(jugador) || controller.isPickingColor();
    }

    /**
     * Agrega una carta al mazo del jugador
     *
     * @param card Carta a agregar
     */
    private void addCardToHBox(Carta card) {
        // TODO: Probablemente haya que cambiar la logica para mostrar las cartas
        addCardToHBox(card, isPlayerHuman);
    }

    /**
     * Ejecuta el turno del jugador actual
     *
     * @param card Carta a jugar
     */
    private void executePlayerTurn(Carta card) throws IOException {
        // Validamos si la carta es jugable, si lo es, jugamos la carta, y ejecutamos su efecto
        if (!Validator.validateCard(card)) return;
        juego.jugarCarta(card);
        card.ejecutarAccion();

        if (jugador.getCantidadDeCartas() == 1 && juego.isCurrentPlayerHuman()){
            if(!controller.triggerShoutUno()) {
                juego.increaseCartasATomar(7);
                juego.darCartasAJugadorActual();
            }
        }
        if (jugador.getCantidadDeCartas() == 0) {
            controller.triggerWinEvent();
            return;
        }

        // Si la carta es saltar turno, saltamos el turno del siguiente jugador
        if (juego.isSaltarTurno()) {
            juego.siguienteJugador();
            return;
        }

        // Si la carta es comodin, triggerear la seleccion de color
        if (card instanceof CartaComodin) {
            controller.triggerChooseColor(card);
            return;
        }

        actions.setText("El jugador jugo la carta " + card.getEtiqueta());
        juego.siguienteJugador();
        if (!juego.isCurrentPlayerHuman())
            controller.triggerCPUTurn();
    }

    /**
     * Agrega una carta al mazo del jugador
     *
     * @param card Carta a agregar
     * @param show Mostrar la carta
     */
    private void addCardToHBox(Carta card, boolean show) {
        boolean disableDeck = getDisabledStatus();
        CardButton cardButton = new CardButton(card, show, (!show || !(!disableDeck && Validator.validateCard(card))));
        cardButton.getButton().setOnAction(event -> {
            juego.guardarJuego();
            try {
                executePlayerTurn(card);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            controller.refreshAll();
        });
        deck.getChildren().add(cardButton.getButton());
        juego.guardarJuego();
    }

    /**
     * Refresca el mazo del jugador
     */
    public void refreshHBox() {
        clearHBox();
        for (Carta carta : jugador.getMazo())
            addCardToHBox(carta);
    }
}
