package com.ucab.proyectouno_fx.controller.GameScreens.decks;

import com.ucab.proyectouno_fx.controller.GameScreens.GameScreenController;
import com.ucab.proyectouno_fx.model.Jugador.Jugador;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class ActiveDecks {
    public ActiveDecks(GameScreenController controller) {
        this.controller = controller;
    }

    private final GameScreenController controller;

    private final List<DeckController> decks = new LinkedList<>();

    /**
     * Mappea uno a uno una lista de jugadores con una lista de contenedores
     *
     * @param playerList        La lista de jugadores a pasar
     * @param mainContainerList La lista de contenedores a introducir Mazos
     */
    public void initializeDecks(List<Jugador> playerList, List<VBox> mainContainerList) {
        assert (playerList.size() == mainContainerList.size());
        try {
            for (int i = 0; i < playerList.size(); i++)
                addPlayer(playerList.get(i), mainContainerList.get(i));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlayer(Jugador player, VBox mainContainer) {
        assert (player != null);
        if (decks.size() >= 2) {
            System.err.println("WARNING: Lista ya contiene 2 jugadores, no es posible agregar otro");
            return;
        }

        decks.add(new DeckController(player, mainContainer, controller));
    }

    public void refreshDecks() {
        for (DeckController deck : decks)
            deck.refreshHBox();
    }
}
