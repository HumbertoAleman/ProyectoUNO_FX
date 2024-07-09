package com.ucab.proyectouno_fx.Controller.GameScreens;

import com.ucab.proyectouno_fx.Controller.ControllerParent;
import com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers.ColorSelector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    private void triggerColorSelection(ActionEvent event, char color) {
        Node node = (Node) event.getSource();
        Stage stage1 = (Stage) node.getScene().getWindow();
        ColorSelector colorSelector = (ColorSelector) stage1.getUserData();
        colorSelector.triggerColorSelection(color);
        stage1.close();
    }


    @FXML
    private void triggerRedSelection(ActionEvent event) {
        triggerColorSelection(event, 'R');
    }

    @FXML
    private void triggerBlueSelection(ActionEvent event) {
        triggerColorSelection(event, 'B');
    }

    @FXML
    private void triggerGreenSelection(ActionEvent event) {
        triggerColorSelection(event, 'G');
    }

    @FXML
    private void triggerYellowSelection(ActionEvent event) {
        triggerColorSelection(event, 'Y');
    }
}
