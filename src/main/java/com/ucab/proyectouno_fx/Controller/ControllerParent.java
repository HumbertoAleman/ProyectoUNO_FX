package com.ucab.proyectouno_fx.Controller;

import com.ucab.proyectouno_fx.ProyectoUNO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class ControllerParent implements Initializable {
    protected static final String registerInputViewFile = "register-input-view.fxml";
    protected static final String registerAuthView = "register-auth-view.fxml";
    protected static final String authenticationInputViewFile = "auth-input-view.fxml";
    protected static final String mainMenuView = "main-menu-view.fxml";
    protected static final String gameScoresView = "game-scores-view.fxml";
    protected static final String gameScreenView = "game-screen-view.fxml";
    protected static final String winnerView = "winner-view.fxml";
    protected static final String loserView = "loser-view.fxml";

    protected static Stage stage;
    protected static Scene scene;

    protected void switchToScene(String newScene) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProyectoUNO.class.getResource(newScene)));
        stage = (Stage) scene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void switchToScene(ActionEvent event, String newScene) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProyectoUNO.class.getResource(newScene)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void exitApp() {
        // NOTA: Esta confirmacion de salir no esta siendo creada usando java fx, no se si a joselito le importe
        // porque tecnicamente cumple con el criterio puesto por el
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Esta seguro de que quiere salir?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
