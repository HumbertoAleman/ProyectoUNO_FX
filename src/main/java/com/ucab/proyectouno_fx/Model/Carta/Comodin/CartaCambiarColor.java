package com.ucab.proyectouno_fx.Model.Carta.Comodin;

public class CartaCambiarColor extends CartaComodin {
    private static final String tipo = "C";

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

    /**
     * Ejecuta la accion de la carta cambiar color
     * @deprecated
     */
    @Override
    public void ejecutarAccion() { }

    /**
     * Obtiene el tipo de la carta cambiar color
     *
     * @return tipo de la carta
     */
    @Override
    public String getTipo() {
        return tipo;
    }
}
