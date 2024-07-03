package com.ucab.proyectouno_fx.controller;

import com.ucab.proyectouno_fx.HelloApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerParent implements Initializable {
    protected static final String registerInputViewFile = "register-input-view.fxml";
    protected static final String registerAuthView = "register-auth-view.fxml";
    protected static final String authenticationInputViewFile = "auth-input-view.fxml";
    protected static final String mainMenuView = "main-menu-view.fxml";
    protected static final String gameScoresView = "game-scores-view.fxml";
    protected static final String gameScreenView = "game-screen-view.fxml";

    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    @FXML
    protected void switchToScene(ActionEvent event, String newScene) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(newScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void exitApp() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
