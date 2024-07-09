package com.ucab.proyectouno_fx.Model.Carta.Comodin;

public class CartaCambiarColor extends CartaComodin {
    /**
     * Constructor de la carta cambiar color
     */
    public CartaCambiarColor() {
        super(CartaCambiarColor.tipo);
    }

    /**
     * Constructor de la carta cambiar color
     *
     * @param color color selecccionado
     */
    public CartaCambiarColor(Character color) {
        super(CartaCambiarColor.tipo);
        colorSeleccionado = color;
    }

    private static final String tipo = "C";

    /**
     * Ejecuta la accion de la carta cambiar color
     *
     * @deprecated
     */
    @Override
    public void ejecutarAccion() {
    }
}
