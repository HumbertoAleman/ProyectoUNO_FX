module com.ucab.proyectouno_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires jdk.compiler;
    requires json.simple;
    requires com.google.gson;

    opens com.ucab.proyectouno_fx to javafx.fxml;
    exports com.ucab.proyectouno_fx;

    opens com.ucab.proyectouno_fx.controller to javafx.fxml;
    exports com.ucab.proyectouno_fx.controller;

    opens com.ucab.proyectouno_fx.model.Carta to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Comodin to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Accion to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Carta.Pila to com.google.gson;
    opens com.ucab.proyectouno_fx.model.Jugador to com.google.gson;
    exports com.ucab.proyectouno_fx.model.Jugador;
}