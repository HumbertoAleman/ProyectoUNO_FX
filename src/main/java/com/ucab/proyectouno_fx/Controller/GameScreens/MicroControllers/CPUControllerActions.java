package com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers;

import com.ucab.proyectouno_fx.Controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.Random;

/**
 * Clase que se encarga de manejar las acciones de la CPU
 */
public class CPUControllerActions {
    private final Juego juego = Juego.getInstance();

    private final ColorSelector colorSelector;

    private final GameScreenController controller;

    /**
     * Constructor de la clase
     *
     * @param controller    controlador de la pantalla de juego
     * @param colorSelector selector de color
     */
    public CPUControllerActions(GameScreenController controller, ColorSelector colorSelector) {
        assert (controller != null);
        assert (colorSelector != null);

        this.controller = controller;
        this.colorSelector = colorSelector;
    }

    /**
     * Metodo que se encarga de manejar la accion de la CPU cuando no juega una carta
     */
    private void cpuDidntPlayCard() {
        System.out.println("Card was not played");
        juego.siguienteJugador();
        controller.refreshAll();
    }

    /**
     * Metodo que se encarga de manejar la accion de la CPU cuando salta el turno
     */
    private void cpuSkipTurn() {
        juego.siguienteJugador();
        triggerCPUTurn();
        controller.refreshAll();
    }

    /**
     * Metodo que se encarga de manejar la accion de la CPU cuando elige un color
     *
     * @param card carta a la que se le asigna el color
     */
    private void cpuPickColor(Carta card) {
        colorSelector.triggerChooseColor(card);
        char[] selection = {'R', 'B', 'G', 'Y'};
        colorSelector.triggerColorSelection(selection[new Random().nextInt(0, 3)]);

        juego.siguienteJugador();

        controller.refreshAll();
    }

    /**
     * Metodo que se encarga de manejar la accion de la CPU cuando es su turno
     */
    public void triggerCPUTurn() {
        if (juego.isCurrentPlayerHuman()) return;

        // Si la carta previa es igual a la nueva carta, entonces no jugamos
        Carta previousTopCard = juego.getTopCard();
        juego.currentPlayerTakeTurn();
        Carta topCard = juego.getTopCard();

        if (juego.getCurrentPlayer().getCantidadDeCartas() == 0) {
            controller.triggerWinEvent();
            return;
        }

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
