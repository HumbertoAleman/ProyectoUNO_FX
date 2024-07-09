package com.ucab.proyectouno_fx.Controller.GameScreens.Decks;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Clase que representa un boton con una carta
 */
public class CardButton {
    private final Button button;

    /**
     * Obtiene el boton
     *
     * @return boton
     */
    public Button getButton() {
        return this.button;
    }

    /**
     * Constructor de la clase
     *
     * @param card       carta
     * @param show       mostrar
     * @param isDisabled deshabilitado
     */
    public CardButton(Carta card, boolean show, boolean isDisabled) {
        String nombreImagen = show ? card.getEtiqueta() : "CartaUNO";

        Image carta;
        try {
            carta = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/" + nombreImagen + ".png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ImageView view = new ImageView(carta);
        view.setPreserveRatio(true);
        view.setFitHeight(160.0);

        button = new Button();
        button.setGraphic(view);
        button.setDisable(isDisabled);
    }
}
