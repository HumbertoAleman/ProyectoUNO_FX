package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.GameScreens.Decks.ActiveDecks;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.CPUControllerActions;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.ColorSelector;
import com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen.LoserViewController;
import com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen.WinnerViewController;
import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Carta.Comodin.CartaComodin;
import com.ucab.proyectouno_fx.Model.Carta.Validator;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;
import com.ucab.proyectouno_fx.ProyectoUNO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
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

    private void playTakenCard() {
        juego.darCartasAJugadorActual();
        Carta cartaTomada = juego.getCurrentPlayer().getMazo().getLast();
        if (!Validator.validateCard(cartaTomada)) {
            juego.siguienteJugador();
            cpuActions.triggerCPUTurn();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Desea jugar esta carta tomada?", ButtonType.YES, ButtonType.NO);

        Image graficoCarta;
        try {
            graficoCarta = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/" + cartaTomada.getEtiqueta() + ".png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView vistaDeCarta = new ImageView(graficoCarta);
        vistaDeCarta.setFitHeight(160.0);
        vistaDeCarta.setPreserveRatio(true);
        alert.setGraphic(vistaDeCarta);

        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO) {
            juego.siguienteJugador();
            cpuActions.triggerCPUTurn();
            return;
        }

        juego.jugarCarta(cartaTomada);
        cartaTomada.ejecutarAccion();

        if (juego.getCurrentPlayer().getCantidadDeCartas() == 1 && juego.isCurrentPlayerHuman()) {
            if(!triggerShoutUno()) {
                juego.increaseCartasATomar(6);
                juego.darCartasAJugadorActual();
            }
        }

        // Si la carta es saltar turno, saltamos el turno del siguiente jugador
        if (juego.isSaltarTurno()) {
            juego.siguienteJugador();
            return;
        }

        // Si la carta es comodin, triggerear la seleccion de color
        if (cartaTomada instanceof CartaComodin) {
            triggerChooseColor(cartaTomada);
        }
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
            if (juego.getCartasATomar() == 0) playTakenCard();
            else {
                juego.darCartasAJugadorActual();
                juego.siguienteJugador();
                cpuActions.triggerCPUTurn();
            }
            refreshAll();
        });
    }

    /**
     * Metodo que se encarga de regresar al menu principal
     *
     * @param resourceBundle Evento de la accion
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
            if (col.equals("Verde")) botonVerde.setGraphic(view);
            if (col.equals("Rojo")) botonRojo.setGraphic(view);
            if (col.equals("Azul")) botonAzul.setGraphic(view);
            if (col.equals("Amarillo")) botonAmarillo.setGraphic(view);
        }
        // Get player list, and create HBOXES and LABELS for them
        activeDecks.initializeDecks(juego.getListaJugadores(), List.of(playerOneContainer, playerTwoContainer));
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

    /**
     * Metodo encargado de cambiar de escena
     *
     * @throws IOException Sera lanzada si hay un error leyendo el archivo
     */
    private void switchToColorPopupScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProyectoUNO.class.getResource(gameColorView)));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 200);
        stage.initModality(Modality.APPLICATION_MODAL);  // Bloquea la opción de clicar la otra pantalla
        stage.setTitle("Cambiar_Color");
        Image icono;
        try {
            icono = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/UNO_Logo.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        stage.getIcons().add(icono);
        stage.setScene(scene);
        stage.setUserData(colorSelector);
        stage.show();
    }

    public void triggerChooseColor(Carta card) {
        try {
            switchToColorPopupScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            juego.registerWinnerScore(true);
            WinnerViewController.setPuntuacionFinal(score);
            try {
                switchToScene(winnerView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        juego.registerWinnerScore(false);
        LoserViewController.setPuntuacionFinal(score);
        try {
            switchToScene(loserView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public boolean triggerShoutUno() {
        long startTime = System.currentTimeMillis();

        Alert unoWindow = new Alert(Alert.AlertType.CONFIRMATION);
        unoWindow.setTitle("Es momento de cantar UNO!");
        unoWindow.setContentText("UNO!");
        unoWindow.showAndWait();

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated Time"+estimatedTime);
        return estimatedTime < 3000;
    }
}
