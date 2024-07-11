package com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers;

import com.ucab.proyectouno_fx.Controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

/**
 * Clase que se encarga de manejar la seleccion de colores en las cartas comodin
 */
public class ColorSelector {
    private final Juego juego = Juego.getInstance();

    private final GameScreenController controller;

    private boolean pickingColor = false;

    /**
     * Metodo que retorna si se esta seleccionando un color
     *
     * @return Retorna un booleano que dicta si se esta seleccionando un color
     */
    public boolean isPickingColor() {
        return pickingColor;
    }

    private CartaComodin comodinBeingPlayed = null;

    /**
     * Constructor de la clase
     *
     * @param controller         Controlador de la pantalla de juego
     */
    public ColorSelector(GameScreenController controller) {
        assert (controller != null);

        this.controller = controller;
    }

    /**
     * Metodo que activa la seleccion de color
     *
     * @param card Carta a la que se le seleccionara el color
     */
    public void triggerChooseColor(Carta card) {
        pickingColor = true;
        comodinBeingPlayed = (CartaComodin) card;
    }

    /**
     * Metodo que selecciona el color
     *
     * @param color Color seleccionado
     */
    public void triggerColorSelection(char color) {
        assert (pickingColor);
        assert (comodinBeingPlayed != null);

        comodinBeingPlayed.setColorSeleccionado(color);
        System.out.println("Color seleccionado: " + color);
        juego.jugarCarta(comodinBeingPlayed);

        comodinBeingPlayed = null;
        pickingColor = false;

        if (juego.isCurrentPlayerHuman()) {
            juego.siguienteJugador();
            juego.currentPlayerTakeTurn();
            juego.siguienteJugador();
        }

        controller.refreshAll();
    }
}
