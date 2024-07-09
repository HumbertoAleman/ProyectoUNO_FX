package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.CPUControllerActions;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.ColorSelector;
import com.ucab.proyectouno_fx.Controller.GameScreens.Decks.ActiveDecks;
import com.ucab.proyectouno_fx.Controller.MainMenuController;
import com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen.LoserViewController;
import com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen.WinnerViewController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Clase controladora de la vista de juego
 */
public class GameScreenController extends ControllerParent {

    private Juego juego;

    private ActiveDecks activeDecks;

    private ColorSelector colorSelector;

    public boolean isPickingColor() {
        return colorSelector.isPickingColor();
    }

    private CPUControllerActions cpuActions;
    /**
     * Metodo que se encarga de inicializar la vista
     */
    public void triggerCPUTurn() {
        cpuActions.triggerCPUTurn();
    }
    /**
     * Metodo que se encarga de inicializar la vista
     */
    private void initializeTakePile() {
        Image carta;

        try {
            carta = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/CartaUno.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ImageView view = new ImageView(carta);
        view.setFitHeight(160.0);
        view.setPreserveRatio(true);

        takePile.setGraphic(view);

        takePile.setOnAction(event -> {
            // Le damos las cartas al jugador actual
            juego.darCartasAJugadorActual();

            // Siguiente jugador ensues
            juego.siguienteJugador();

            // Triggereamos el turno del CPU
            cpuActions.triggerCPUTurn();

            refreshAll();
        });
    }
    /**
     * Metodo que se encarga de regresar al menu principal
     * @param resourceBundle Evento de la accion
     * @throws IOException Excepcion lanzada si hay un error en la lectura del archivo
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.juego = Juego.getInstance();
        this.activeDecks = new ActiveDecks(this);
        this.colorSelector = new ColorSelector(this, colorPickContainer, takePile);
        this.cpuActions = new CPUControllerActions(this, colorSelector);

        Image color;
        String[] colores = {"Verde", "Rojo", "Azul", "Amarillo"};
        for (String col : colores) {
            try {
                color = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/Color" + col + ".png"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImageView view = new ImageView(color);
            view.setFitHeight(40.0);
            view.setPreserveRatio(true);
            if(col.equals("Verde"))
                botonVerde.setGraphic(view);
            if(col.equals("Rojo"))
                botonRojo.setGraphic(view);
            if(col.equals("Azul"))
                botonAzul.setGraphic(view);
            if(col.equals("Amarillo"))
                botonAmarillo.setGraphic(view);
        }
        // Get player list, and create HBOXES and LABELS for them
        activeDecks.initializeDecks(juego.getPlayers(), List.of(playerOneContainer, playerTwoContainer));
        humanPlayersNameLabel.setText(MainMenuController.getActiveUser());

        initializeTakePile();
        refreshPlayPile();
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
    private Button botonRojo, botonAzul, botonAmarillo, botonVerde;

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

    public void triggerChooseColor(Carta card) throws IOException {
        switchToColorPopupScene(gameColorView);
        colorSelector.triggerChooseColor(card);
    }

    public void refreshAll() {
        refreshDecks();
        refreshPlayPile();
    }

    private void refreshDecks() {
        activeDecks.refreshDecks();
    }
    /**
     * Metodo que se encarga de refrescar la pila de juego
     */
    private void refreshPlayPile() {
        Image carta;

        try {
            carta = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/" + juego.getTopCard().getEtiqueta() + ".png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ImageView view = new ImageView(carta);
        view.setFitHeight(160.0);
        view.setPreserveRatio(true);

        playPile.setGraphic(view);
    }
    /**
     * Metodo que se encarga de lanzar el evento de ganar
     */
    public void triggerWinEvent() {
        int score = juego.getWinnerScore();

        if (juego.isCurrentPlayerHuman()) {
            juego.registerWinner(true);
            WinnerViewController.setPuntuacionFinal(score);
            try {
                switchToScene(winnerView);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return;
        }

        juego.registerWinner(false);
        LoserViewController.setPuntuacionFinal(score);
        try {
            switchToScene(loserView);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
