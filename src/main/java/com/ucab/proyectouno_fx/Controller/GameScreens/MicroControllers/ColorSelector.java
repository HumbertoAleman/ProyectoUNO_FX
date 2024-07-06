package com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers;

import com.ucab.proyectouno_fx.Controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ColorSelector {
    private final Juego juego = Juego.getInstance();

    private final GameScreenController controller;

    private final GridPane colorPickContainer;

    private final Button takePile;

    private boolean pickingColor = false;

    public boolean isPickingColor() {
        return pickingColor;
    }

    private CartaComodin comodinBeingPlayed = null;

    public ColorSelector(GameScreenController controller, GridPane colorPickContainer, Button takePile) {
        assert (controller != null);
        assert (colorPickContainer != null);
        assert (takePile != null);

        this.controller = controller;
        this.colorPickContainer = colorPickContainer;
        this.takePile = takePile;
    }

    public void setColorPickerDisable(boolean disable) {
        takePile.setDisable(!disable);
        for (Node child : colorPickContainer.getChildren())
            child.setDisable(disable);
    }

    public void triggerChooseColor(Carta card) {
        pickingColor = true;
        setColorPickerDisable(false);
        comodinBeingPlayed = (CartaComodin) card;
    }

    public void triggerColorSelection(char color) {
        assert (pickingColor);
        assert (comodinBeingPlayed != null);

        comodinBeingPlayed.setColorSeleccionado(color);
        juego.jugarCarta(comodinBeingPlayed);

        comodinBeingPlayed = null;
        pickingColor = false;

        if (juego.isCurrentPlayerHuman()) {
            juego.siguienteJugador();
            juego.currentPlayerTakeTurn();
            juego.siguienteJugador();
        }

        controller.refreshAll();
        setColorPickerDisable(true);
    }
}
