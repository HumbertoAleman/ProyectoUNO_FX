package com.ucab.proyectouno_fx.controller.decks;

import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Validator;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CardButton {
    private Juego juego = Juego.getInstance();

    private Button button;

    public Button getButton() {
        return this.button;
    }

    private Carta card;

    public Carta getCard() {
        return this.card;
    }

    public void setButtonAction(EventHandler<ActionEvent> event) {
        button.setOnAction(event);
    }

    public CardButton(Carta card, boolean show, boolean isDisabled) {
        this.card = card;
        this.button = new Button(show ? card.getEtiqueta() : "UNO");
        this.button.setDisable(isDisabled);
    }
}
