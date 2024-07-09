package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Clase controladora de la vista de autenticacion y registro
 */
public class RegisterAuthController extends ControllerParent {
    @FXML
    public void onRegisterButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, registerInputViewFile);
    }

    /**
     * Metodo que se encarga de cambiar la vista a la de autenticacion
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void onAuthButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, authenticationInputViewFile);
    }

    /**
     * Metodo que se encarga de cerrar la aplicacion
     */
    @FXML
    public void onExitButtonPress() {
        exitApp();
    }
}
