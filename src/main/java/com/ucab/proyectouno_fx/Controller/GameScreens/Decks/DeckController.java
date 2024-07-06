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

    private void addCardToHBox(Carta card) {
        // TODO: Probablemente haya que cambiar la logica para mostrar las cartas
        addCardToHBox(card, isPlayerHuman);
    }

    private void executePlayerTurn(Carta card) {
        // Validamos si la carta es jugable, si lo es, jugamos la carta, y ejecutamos su efecto
        if (!Validator.validateCard(card)) return;
        juego.jugarCarta(card);
        card.ejecutarAccion();

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
            actions.setText("Escoja un color");
            controller.triggerChooseColor(card);
            return;
        }

        actions.setText("El jugador jugo la carta " + card.getEtiqueta());
        juego.siguienteJugador();
        if (!juego.isCurrentPlayerHuman())
            controller.triggerCPUTurn();
    }

    private void addCardToHBox(Carta card, boolean show) {
        boolean disableDeck = getDisabledStatus();
        CardButton cardButton = new CardButton(card, show, (!show || !(!disableDeck && Validator.validateCard(card))));
        cardButton.getButton().setOnAction(event -> {
            juego.guardarJuego();
            executePlayerTurn(card);
            controller.refreshAll();
        });
        deck.getChildren().add(cardButton.getButton());
    }

    public void refreshHBox() {
        clearHBox();
        for (Carta carta : jugador.getMazo())
            addCardToHBox(carta);
    }
}
