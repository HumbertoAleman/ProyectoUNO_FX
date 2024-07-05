package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.CPUControllerActions;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.ColorSelector;
import com.ucab.proyectouno_fx.Controller.GameScreens.Decks.ActiveDecks;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController extends ControllerParent {

    private final Juego juego = Juego.getInstance();

    private ActiveDecks activeDecks;

    private ColorSelector colorSelector;

    public boolean isPickingColor() {
        return colorSelector.isPickingColor();
    }

    private CPUControllerActions cpuActions;

    public void triggerCPUTurn() {
        cpuActions.triggerCPUTurn();
    }


    public void refreshAll() {
        refreshDecks();
        refreshPlayPile();
    }

    public void refreshDecks() {
        activeDecks.refreshDecks();
    }

    public void refreshPlayPile() {
        playPile.setText(juego.getTopCard().getEtiqueta());
    }

    private List<VBox> getListOfPlayerContainers() {
        return List.of(playerOneContainer, playerTwoContainer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.activeDecks = new ActiveDecks(this);
        this.colorSelector = new ColorSelector(this, colorPickContainer);
        this.cpuActions = new CPUControllerActions(this, colorSelector);

        // Initialize game, then get player list, and create HBOXES and LABELS for them
        juego.initializeGame();
        activeDecks.initializeDecks(juego.getPlayers(), getListOfPlayerContainers());
        humanPlayersNameLabel.setText(MainMenuController.getActiveUser());

        takePile.setText("UNO");
        takePile.setOnAction(event -> {
            // Le damos las cartas al jugador actual
            juego.darCartas();

            // Siguiente jugador ensues
            juego.siguienteJugador();

            // Triggereamos el turno del CPU
            cpuActions.triggerCPUTurn();

            refreshAll();
        });

        playPile.setText(juego.getTopCard().getEtiqueta());

        colorSelector.setColorPickerDisable(true);

        refreshAll();
    }

    @FXML
    private VBox playerOneContainer;

    @FXML
    private VBox playerTwoContainer;

    @FXML
    private Button playPile;

    @FXML
    private Button takePile;

    @FXML
    private Label humanPlayersNameLabel;

    @FXML
    private GridPane colorPickContainer;

    @FXML
    private void triggerRedSelection() {
        colorSelector.triggerColorSelection('R');
    }

    @FXML
    private void triggerBlueSelection() {
        colorSelector.triggerColorSelection('B');
    }

    @FXML
    private void triggerGreenSelection() {
        colorSelector.triggerColorSelection('G');
    }

    @FXML
    private void triggerYellowSelection() {
        colorSelector.triggerColorSelection('Y');
    }

    @FXML
    private void returnToMainMenu(ActionEvent event) throws IOException {
        switchToScene(event, mainMenuView);
    }

    public void triggerChooseColor(Carta card) {
        colorSelector.triggerChooseColor(card);
    }
}
