module com.ucab.proyectouno_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires jdk.compiler;
    requires json.simple;
    requires com.google.gson;

    opens com.ucab.proyectouno_fx to javafx.fxml;

    opens com.ucab.proyectouno_fx.controller to javafx.fxml;

    opens com.ucab.proyectouno_fx.model.Carta to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Comodin to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Accion to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Pila to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Jugador to com.google.gson;
    opens com.ucab.proyectouno_fx.controller.GameScreens.decks to javafx.fxml;
    opens com.ucab.proyectouno_fx.controller.GameScreens to javafx.fxml;

    exports com.ucab.proyectouno_fx.model.Jugador;
    exports com.ucab.proyectouno_fx.controller.GameScreens.decks;
    exports com.ucab.proyectouno_fx.controller;
    exports com.ucab.proyectouno_fx;

    exports com.ucab.proyectouno_fx.model.Carta;
    exports com.ucab.proyectouno_fx.model.Controlador;
    exports com.ucab.proyectouno_fx.controller.GameScreens;
    exports com.ucab.proyectouno_fx.controller.GameScreens.MicroControllers;
    opens com.ucab.proyectouno_fx.controller.GameScreens.MicroControllers to javafx.fxml;
}