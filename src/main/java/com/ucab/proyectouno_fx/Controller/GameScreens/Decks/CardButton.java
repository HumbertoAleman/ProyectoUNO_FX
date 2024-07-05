package com.ucab.proyectouno_fx.Controller.GameScreens.Decks;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import javafx.scene.control.Button;

public class CardButton {
    private final Button button;

    public Button getButton() {
        return this.button;
    }

    public CardButton(Carta card, boolean show, boolean isDisabled) {
        this.button = new Button(show ? card.getEtiqueta() : "UNO");
        this.button.setDisable(isDisabled);
    }
}
