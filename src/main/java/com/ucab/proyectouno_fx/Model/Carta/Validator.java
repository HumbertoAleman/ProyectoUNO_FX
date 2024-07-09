package com.ucab.proyectouno_fx.Model.Carta;

import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.Objects;

public interface Validator {
    /**
     * Valida si una carta se puede jugar
     *
     * @param carta una carta
     * @return true si se puede jugar o false si no se puede jugar
     */
    static boolean validateCard(Carta carta) {
        Juego juego = Juego.getInstance();

        Carta topCard = juego.getTopCard();
        assert (topCard != null);

        if (carta == null)
            return false;

        if (Objects.equals(carta.getTipo(), "T4"))
            return true;

        if (juego.getCartasATomar() > 0)
            return topCard.getTipo().equals(carta.getTipo());

        return carta.getTipo().equals("C") ||
                topCard.getColor() == carta.getColor() ||
                topCard.getTipo().equals(carta.getTipo());
    }
}
