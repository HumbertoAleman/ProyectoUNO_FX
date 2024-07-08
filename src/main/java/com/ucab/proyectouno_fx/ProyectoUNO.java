package com.ucab.proyectouno_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProyectoUNO extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProyectoUNO.class.getResource("register-auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("UNO_FX");
        stage.setScene(scene);
        Image icono;
        try {
            icono = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/UNO_Logo.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        stage.getIcons().add(icono);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}