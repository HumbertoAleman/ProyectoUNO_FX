package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameColorController extends ControllerParent {

    @FXML
    private Button botonRojo, botonAzul, botonAmarillo, botonVerde;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image color;
        String[] colores = {"Verde", "Rojo", "Azul", "Amarillo"};
        for (String col : colores) {
            try {
                color = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/Color" + col + ".png"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImageView view = new ImageView(color);
            view.setFitHeight(50.0);
            view.setPreserveRatio(true);
            if (col.equals("Verde"))
                botonVerde.setGraphic(view);
            if (col.equals("Rojo"))
                botonRojo.setGraphic(view);
            if (col.equals("Azul"))
                botonAzul.setGraphic(view);
            if (col.equals("Amarillo"))
                botonAmarillo.setGraphic(view);
        }
    }

    @FXML
    private void triggerRedSelection() {
        //colorSelector.triggerColorSelection('R');
    }

    @FXML
    private void triggerBlueSelection() {
        //colorSelector.triggerColorSelection('B');
    }

    @FXML
    private void triggerGreenSelection() {
        //colorSelector.triggerColorSelection('G');
    }

    @FXML
    private void triggerYellowSelection() {
        //colorSelector.triggerColorSelection('Y');
    }
}
