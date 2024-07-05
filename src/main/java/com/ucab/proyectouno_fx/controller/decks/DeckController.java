package com.ucab.proyectouno_fx.controller.decks;

import com.ucab.proyectouno_fx.controller.GameScreenController;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Carta.Validator;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import com.ucab.proyectouno_fx.model.Jugador.Humano;
import com.ucab.proyectouno_fx.model.Jugador.Jugador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

        assert(controller != null);
        this.controller = controller;
    }

    private GameScreenController controller;

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
        return !juego.getCurrentPlayer().equals(jugador);
    }

    private void addCardToHBox(Carta card) {
        // TODO: Probablemente haya que cambiar la logica para mostrar las cartas
        addCardToHBox(card, isPlayerHuman);
    }

    private void addCardToHBox(Carta card, boolean show) {
        boolean disableDeck = getDisabledStatus();
        CardButton cardButton = new CardButton(card, show, (!show || (disableDeck || !Validator.validateCard(card))));
        cardButton.getButton().setOnAction(event -> {
            if (Validator.validateCard(card)) {
                juego.jugarCarta(card);

                if (card instanceof CartaComodin) {
                    actions.setText("Escoja un color");
                    controller.triggerChooseColor(card);
                    return;
                }

                card.ejecutarAccion();

                actions.setText("El jugador jugo la carta " + card.getEtiqueta());
                juego.siguienteJugador();
                if (!juego.isCurrentPlayerHuman()) {
                    controller.triggerCurrentPlayerTurn();
                    controller.refreshDecks();
                    controller.refreshPlayPile();
                }
            }
            controller.refreshDecks();
            controller.refreshPlayPile();
        });
        deck.getChildren().add(cardButton.getButton());
    }

    public void refreshHBox() {
        clearHBox();
        for (Carta carta : jugador.getMazo())
            addCardToHBox(carta);
    }

    public void refreshHBox(boolean show) {
        clearHBox();
        for (Carta carta : jugador.getMazo())
            addCardToHBox(carta, show);
    }
}
