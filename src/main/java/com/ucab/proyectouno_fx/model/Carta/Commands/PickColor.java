package com.ucab.proyectouno_fx.model.Carta.Commands;

import com.ucab.proyectouno_fx.controller.GameScreenController;

public class PickColor implements CardCommand {

    private GameScreenController receiver;

    public PickColor(GameScreenController receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.enableColorChooser();
    }
}
