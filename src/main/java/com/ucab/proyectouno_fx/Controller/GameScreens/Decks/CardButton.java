package com.ucab.proyectouno_fx.Controller.GameScreens.Decks;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CardButton {
    private final Button button;

    public Button getButton() {
        return this.button;
    }

    public CardButton(Carta card, boolean show, boolean isDisabled) {
        //this.button = new Button(show ? card.getEtiqueta() : "CartaUno");
        this.button = new Button();
        Image carta = null;
        try {
            carta = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/" + card.getEtiqueta() + ".png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        button.setGraphic(new ImageView(carta));
        this.button.setDisable(isDisabled);
    }
}
