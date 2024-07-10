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
    /**
     * Metodo para comenzar el juego
     *
     * @param stage Escenario en donde colocar las escenas
     * @throws IOException Excepcion a lanzar si ocurre un error de IO
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProyectoUNO.class.getResource("register-auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("UNO_FX");
        stage.setScene(scene);
        Image icono = null;
        try {
            icono = new Image(new FileInputStream("src/main/resources/com/ucab/proyectouno_fx/images/UNO_Logo.png"));
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Fallo en cargar el icono del programa");
        }
        if (icono != null)
            stage.getIcons().add(icono);
        stage.show();
    }

    /**
     * Metodo main para lanzar la aplicacion
     *
     * @param args Argumentos de la aplicacion
     */
    public static void main(String[] args) {
        launch();
    }
}