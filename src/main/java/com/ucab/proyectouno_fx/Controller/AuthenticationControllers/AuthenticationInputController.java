package com.ucab.proyectouno_fx.Controller.AuthenticationControllers;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.Model.Controlador.ManejadorSesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controladora de la vista de autenticacion
 */
public class AuthenticationInputController extends ControllerParent {
    @FXML
    public Button authenticateButton;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public Label mensajeAUsuario;

    private final static String errorStyle = "-fx-text-fill: red;";

    private ManejadorSesion manejadorSesion;

    @Override
/**
 * Inicializa la vista de autenticacion
 */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manejadorSesion = ManejadorSesion.getInstance();
        clearMessage();
    }

    /**
     * Limpia el mensaje de error
     */
    private void clearMessage() {
        mensajeAUsuario.setText(null);
        mensajeAUsuario.setVisible(false);
    }

    /**
     * Muestra un mensaje de error
     *
     * @param mensaje
     */
    private void sendErrorMessage(String mensaje) {
        mensajeAUsuario.setText(mensaje);
        mensajeAUsuario.setVisible(true);
        mensajeAUsuario.setStyle(errorStyle);
    }

    /**
     * Valida el nombre de usuario
     *
     * @param username
     * @return
     */
    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            sendErrorMessage("ERROR: Debe ingresar un nombre de usuario");
            return false;
        }

        if (!manejadorSesion.userDirExists(username)) {
            sendErrorMessage("ERROR: El usuario de nombre " + username + " no existe");
            return false;
        }

        return true;
    }

    /**
     * Valida el login
     *
     * @param username
     * @param password
     * @return
     */
    private boolean validateLogin(String username, String password) {
        if (!manejadorSesion.loginPlayerDirectory(username, password)) {
            sendErrorMessage("ERROR: La contraseña ingresada no es la correcta");
            return false;
        }

        return true;
    }

    /**
     * Autentica al usuario
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void authenticateUser(ActionEvent event) throws IOException {
        String username, password;
        username = textField.getText();
        password = passwordField.getText();

        if (!validateUsername(username)) return;
        if (!validateLogin(username, password)) return;

        MainMenuController.setActiveUser(username);
        Juego.getInstance().cargarScores();
        switchToScene(event, mainMenuView);
    }

    /**
     * Regresa a la vista de registro
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void returnToRegisterAuthView(ActionEvent event) throws IOException {
        switchToScene(event, registerAuthView);
    }

    /**
     * Maneja el evento de tecla presionada en el campo de usuario
     *
     * @param keyEvent
     */
    @FXML
    public void userFieldKeyPressed(KeyEvent keyEvent) {
        clearMessage();
        if (!keyEvent.getCode().toString().equals("ENTER")) return;
        if (passwordField.getText().isEmpty()) passwordField.requestFocus();
        else authenticateButton.fire();
    }

    /**
     * Maneja el evento de tecla presionada en el campo de contraseña
     *
     * @param keyEvent
     */
    @FXML
    public void passwordFieldKeyPressed(KeyEvent keyEvent) {
        clearMessage();
        if (!keyEvent.getCode().toString().equals("ENTER")) return;
        authenticateButton.fire();
    }
}
