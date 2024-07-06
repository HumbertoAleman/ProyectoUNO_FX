package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Model.Controlador.ManejadorSesion;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterInputController extends ControllerParent {
    @FXML
    private Label mensajeAUsuario;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    private final static String errorStyle = "-fx-text-fill: red;";

    private final static String successStyle = "-fx-text-fill: green;";

    private ManejadorSesion manejadorSesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manejadorSesion = ManejadorSesion.getInstance();
        clearMessage();
    }

    private void clearMessage() {
        mensajeAUsuario.setVisible(false);
        mensajeAUsuario.setText(null);
    }

    private void sendErrorMessage(String message) {
        mensajeAUsuario.setVisible(true);
        mensajeAUsuario.setStyle(errorStyle);
        mensajeAUsuario.setText(message);
    }

    private void sendSuccessMessage(String message) {
        mensajeAUsuario.setVisible(true);
        mensajeAUsuario.setStyle(successStyle);
        mensajeAUsuario.setText(message);
    }

    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            sendErrorMessage("ERROR: El nombre de usuario no puede estar vacio");
            return false;
        }

        if (username.length() < 4) {
            sendErrorMessage("ERROR: El nombre de usuario no puede tener menos de 4 caracteres");
            return false;
        }

        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            sendErrorMessage("ERROR: La contraseña no puede estar vacia");
            return false;
        }

        if (password.length() < 8) {
            sendErrorMessage("ERROR: La contraseña no puede tener menos de 8 caracteres");
            return false;
        }

        return true;
    }

    @FXML
    public void registerUser() {
        String username, password;
        username = textField.getText();
        password = passwordField.getText();

        if (!validateUsername(username)) return;

        if (manejadorSesion.userDirExists(username)) {
            sendErrorMessage("ERROR: Un usuario con este nombre ya se encuentra registrado");
            return;
        }

        if (!validatePassword(password)) return;

        manejadorSesion.registerPlayerDirectory(username, password);
        sendSuccessMessage("Usuario " + username + " registrado correctamente, puede regresar al menu principal");
    }

    @FXML
    public void returnToRegisterAuthView(ActionEvent event) throws IOException {
        switchToScene(event, registerAuthView);
    }

    @FXML
    public void userFieldKeyPressed(KeyEvent keyEvent) {
        clearMessage();
        if (!keyEvent.getCode().toString().equals("ENTER")) return;

        if (passwordField.getText().isEmpty()) passwordField.requestFocus();
        else registerUser();
    }

    @FXML
    public void passwordFieldKeyPressed(KeyEvent keyEvent) {
        clearMessage();
        if (!keyEvent.getCode().toString().equals("ENTER")) return;
        registerUser();
    }
}
