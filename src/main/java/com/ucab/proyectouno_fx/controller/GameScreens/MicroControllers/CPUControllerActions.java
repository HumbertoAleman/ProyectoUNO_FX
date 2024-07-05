package com.ucab.proyectouno_fx.controller.GameScreens.MicroControllers;

import com.ucab.proyectouno_fx.controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.model.Carta.Carta;
import com.ucab.proyectouno_fx.model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.model.Controlador.Juego;

import java.util.Random;

public class CPUControllerActions {
    Juego juego = Juego.getInstance();

    private final ColorSelector colorSelector;

    private final GameScreenController controller;

    public CPUControllerActions(GameScreenController controller, ColorSelector colorSelector) {
        assert (controller != null);
        assert (colorSelector != null);

        this.controller = controller;
        this.colorSelector = colorSelector;
    }

    private void cpuDidntPlayCard() {
        System.out.println("Card was not played");
        juego.siguienteJugador();
        controller.refreshAll();
    }

    private void cpuSkipTurn() {
        juego.siguienteJugador();
        triggerCPUTurn();
        controller.refreshAll();
    }

    private void cpuPickColor(Carta card) {
        colorSelector.triggerChooseColor(card);
        char[] selection = {'R', 'B', 'G', 'Y'};
        colorSelector.triggerColorSelection(selection[new Random().nextInt(0, 3)]);

        juego.siguienteJugador();

        controller.refreshAll();
    }

    public void triggerCPUTurn() {
        if (juego.isCurrentPlayerHuman()) return;

        // Si la carta previa es igual a la nueva carta, entonces no jugamos
        Carta previousTopCard = juego.getTopCard();
        juego.currentPlayerTakeTurn();
        Carta topCard = juego.getTopCard();

        if (previousTopCard == topCard) {
            cpuDidntPlayCard();
            return;
        }

        topCard.ejecutarAccion();

        if (juego.isSaltarTurno()) {
            cpuSkipTurn();
            return;
        }

        if (topCard instanceof CartaComodin) {
            cpuPickColor(topCard);
            return;
        }

        juego.siguienteJugador();

        controller.refreshAll();
    }
}
