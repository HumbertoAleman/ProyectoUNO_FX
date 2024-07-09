package com.ucab.proyectouno_fx.Model.Carta;

public class CartaNumerica extends CartaColorada {
    /**
     * Constructor de la carta numerica
     */
    public CartaNumerica() {
    }

    /**
     * Constructor de la carta numerica
     *
     * @param color  color de la carta
     * @param numero numero de la carta
     */
    public CartaNumerica(char color, String numero) {
        super(color);
        this.numero = numero;
    }

    private String numero;

    /**
     * Ejecuta la accion de la carta
     */
    @Override
    public void ejecutarAccion() {
        // Las cartas numericas no tienen accion
    }

    /**
     * Obtiene el tipo de la carta numerica
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return numero;
    }

    @Override
    public int getScore() {
        return Integer.parseInt(getTipo());
    }
}
