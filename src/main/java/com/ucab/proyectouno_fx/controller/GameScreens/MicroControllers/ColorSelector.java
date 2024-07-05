package com.ucab.proyectouno_fx.controller.GameScreens.MicroControllers;

import com.ucab.proyectouno_fx.controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Controlador.Juego;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ColorSelector {
    private final Juego juego = Juego.getInstance();

    private final GameScreenController controller;

    private final GridPane colorPickContainer;

    private boolean pickingColor = false;

    public boolean isPickingColor() {
        return pickingColor;
    }

    private CartaComodin comodinBeingPlayed = null;

    public ColorSelector(GameScreenController controller, GridPane colorPickContainer) {
        assert (controller != null);
        assert (colorPickContainer != null);

        this.controller = controller;
        this.colorPickContainer = colorPickContainer;
    }

    public void setColorPickerDisable(boolean disable) {
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
        }

        controller.refreshAll();

        setColorPickerDisable(true);
    }
}
