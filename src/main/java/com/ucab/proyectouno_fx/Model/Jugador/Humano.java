package com.ucab.proyectouno_fx.Model.Jugador;

import com.ucab.proyectouno_fx.Model.Carta.Carta;
import com.ucab.proyectouno_fx.Model.Controlador.Juego;

import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jugador {
    private final Juego juego = Juego.getInstance();

    /**
     * Costructor de humano
     *
     * @param nombre nombre del jugador
     */
    public Humano(String nombre) {
        super(nombre);
        tipo = "H";
    }

    /**
     * El jugador realiza una accion en su turno
     *
     * @return Retorna si el jugador va a salir al menu principal
     */
    @Override
    public boolean tomarTurno() {
        Scanner scanner = new Scanner(System.in);
        Carta cartaSeleccionada = null;
        String seleccion = "";

        while (!juego.jugarCarta(cartaSeleccionada) && !seleccion.equals("T") && !seleccion.equals("0")) {
            ImpresoraCarta.mostrarMazo(mazo, true);

            System.out.println();
            System.out.println("Es el turno de: " + getNombre());
            if (juego.getCartasATomar() > 0)
                System.out.println("Ingrese T para tomar " + juego.getCartasATomar() + " cartas");
            else System.out.println("Ingrese T para tomar una carta");
            System.out.println();
            System.out.println("Presione 0 para regresar al menu principal");

            System.out.print("Escriba la carta a jugar: ");
            seleccion = scanner.nextLine().toUpperCase();

            // Esta linea existe porque por alguna razon si usamos una variable dentro de una lamda function
            // tiene que ser de esta manera
            String finalSeleccion = seleccion;
            cartaSeleccionada = mazo.stream().filter(carta -> (carta.getColor() + carta.getTipo()).equals(finalSeleccion)).findFirst().orElse(null);
        }

        if (seleccion.equals("T")) {
            ArrayList<Carta> cartasAgregadas = juego.darCartas();
            if (cartasAgregadas.size() == 1) {
                Carta cartaTomada = cartasAgregadas.getFirst();
                while (!seleccion.equals("S") && !seleccion.equals("N")) {
                    System.out.println("La carta tomada fue ");
                    System.out.println("+---+");
                    ImpresoraCarta.imprimirCuerpoCarta(cartaTomada, true);
                    System.out.println();
                    System.out.println("+---+");
                    System.out.println();
                    System.out.println("Desea jugar esta carta?");
                    System.out.println("S. Si");
                    System.out.println("N. No");
                    System.out.println();
                    System.out.print("Seleccion ");
                    seleccion = scanner.nextLine().toUpperCase();
                }

                if (seleccion.equals("N")) return true;

                if (juego.jugarCarta(cartaTomada)) {
                    System.out.println("Si se pudo jugar la carta tomada");
                    mazo.remove(cartaTomada);
                } else System.out.println("No se pudo jugar la carta tomada");
                return true;
            }
            return true;
        }

        if (seleccion.equals("0")) return false;

        mazo.remove(cartaSeleccionada);
        return true;
    }


    //Calcula el tiempo desde que se ejecuta la funcion hasta que se recibe la entrada del usuario
    //Si es menor a 3 segundos es considerado valido y retorna true

    /**
     * Le da 3 segundos al jugador para cantar uno, sino agarrara cartas
     *
     * @return true si el jugador canta uno en menos de 3 segundos, false si no lo hace a tiempo
     */
    public boolean cantarUno() {
        Scanner scanner = new Scanner(System.in);
        long tiempoInicio = System.currentTimeMillis();
        System.out.println("Es momento de cantar UNO!\nPresiona la tecla U y ENTER rapido!");
        if (scanner.nextLine().equalsIgnoreCase("U")) {
            long tiempoFinal = System.currentTimeMillis();
            if (tiempoFinal - tiempoInicio >= 3000) {
                System.out.println("No presionaste la tecla a tiempo :(");
                return false;
            }
            return true;
        } else {
            System.out.println("Ingresaste la letra incorrecta :(");
            return false;
        }
    }
}
