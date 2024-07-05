package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterAuthController extends ControllerParent {
    @FXML
    public void onRegisterButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, registerInputViewFile);
    }

    @FXML
    public void onAuthButtonPress(ActionEvent event) throws IOException {
        switchToScene(event, authenticationInputViewFile);
    }

    @FXML
    public void onExitButtonPress() {
        exitApp();
    }
}
