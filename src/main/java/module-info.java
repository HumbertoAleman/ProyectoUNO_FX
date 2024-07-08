module com.ucab.proyectouno_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires jdk.compiler;
    requires json.simple;
    requires com.google.gson;

    opens com.ucab.proyectouno_fx.Model.Controlador to com.google.gson;

    opens com.ucab.proyectouno_fx to javafx.fxml;

    opens com.ucab.proyectouno_fx.Controller to javafx.fxml;

    opens com.ucab.proyectouno_fx.Model.Carta to com.google.gson;
    opens com.ucab.proyectouno_fx.Model.Carta.Comodin to com.google.gson;
    opens com.ucab.proyectouno_fx.Model.Carta.Accion to com.google.gson;
    opens com.ucab.proyectouno_fx.Model.Carta.Pila to com.google.gson;
    opens com.ucab.proyectouno_fx.Model.Jugador to com.google.gson;

    opens com.ucab.proyectouno_fx.Controller.GameScreens.Decks to javafx.fxml;
    opens com.ucab.proyectouno_fx.Controller.GameScreens to javafx.fxml;
    opens com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers to javafx.fxml;

    exports com.ucab.proyectouno_fx.Model.Jugador;
    exports com.ucab.proyectouno_fx.Controller.GameScreens.Decks;
    exports com.ucab.proyectouno_fx.Controller;
    exports com.ucab.proyectouno_fx;

    exports com.ucab.proyectouno_fx.Model.Carta;
    exports com.ucab.proyectouno_fx.Model.Controlador;
    exports com.ucab.proyectouno_fx.Controller.GameScreens;
    exports com.ucab.proyectouno_fx.Controller.GameScreens.MicroControllers;
    exports com.ucab.proyectouno_fx.Model.Carta.Pila;
    exports com.ucab.proyectouno_fx.Controller.AuthenticationControllers;
    opens com.ucab.proyectouno_fx.Controller.AuthenticationControllers to javafx.fxml;
    exports com.ucab.proyectouno_fx.Model.Controlador.Guardador;
    opens com.ucab.proyectouno_fx.Model.Controlador.Guardador to com.google.gson;
    exports com.ucab.proyectouno_fx.Model.Controlador.Cargador;
    opens com.ucab.proyectouno_fx.Model.Controlador.Cargador to com.google.gson;
    exports com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen;
    opens com.ucab.proyectouno_fx.Controller.GameScreens.ResultScreen to javafx.fxml;
    exports com.ucab.proyectouno_fx.Model.Controlador.Score;
    opens com.ucab.proyectouno_fx.Model.Controlador.Score to com.google.gson;
}