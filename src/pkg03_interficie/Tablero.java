/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg03_interficie;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Daniel Salanova Dmitriyev GRUPO 2 
 * Video explicativo: https://www.youtube.com/watch?v=Wm7jBxtVNL4
 * 
 */
public class Tablero extends JPanel {

    private static String[] orden = {"clubs", "diamonds", "hearts", "spades"};
    private Carta[][] cartas; //Para la iniciazion del tablero
    private Carta[] cartasJugador = new Carta[13]; //Cartas del jugador

    public Tablero() {
        super();
        cartas = new Carta[4][13]; //Creamos el tablero
        this.inicializarTablero(); //Inicializamos el tablero
        this.setLayout(new GridLayout(5, 13, 2, 2));
        this.setBackground(new Color(8, 100, 4));

        //Añadimos las cartas dentro del JPanel
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 0; j < cartas[0].length; j++) {
                this.add(cartas[i][j]);
            }
        }

        //Creamos los huecos para las cartas del Jugador 1
        for (int k = 0; k < cartasJugador.length; k++) {
            Carta c = new Carta();
            c.fondoCarta("Cartes/colorTablero.png"); //Solo sale el recuadro en verde oscuro
            cartasJugador[k] = c;
            this.add(c);
        }

    }

    //Con este método pretendemos colocar dentro del array de cartas
    //todas las cartas ordenadas según su palo y en orden ascendente
    //de puntuación.
    public void inicializarTablero() {
        for (int i = 0; i < 4; i++) {
            Carta c = new Carta();
            for (int j = 1; j < 14; j++) {
                int puntuacion = j;
                String tipo = orden[i];
                String nombreImg = "" + puntuacion + "_of_" + tipo + ".png";
                c = new Carta(nombreImg, tipo, puntuacion);
                cartas[i][j - 1] = c;
            }
        }
    }

    //Barajado de cartas siguiendo el algoritmo de Durstenfeld
    public void barajarCartas() {
        Carta[] cartasTratamiento = transformacionUnidimensional();
        Random r = new Random();

        //Indice en 1 ya que con 0 siempre será el mismo elemento barajado en la iteracion 0
        for (int indice = 1; indice < cartasTratamiento.length; indice++) {
            int seleccionado = r.nextInt(indice + 1);
            Carta aux = cartasTratamiento[seleccionado];
            cartasTratamiento[seleccionado] = cartasTratamiento[indice];
            cartasTratamiento[indice] = aux;
        }

        //Sustituimos el array de cartas del tablero por las cartas barajadas
        transformacionBidimensional(cartasTratamiento);

        //Volvemos a pintar el tablero
        this.repintar();

    }

    /*
        Método auxiliar para la mezcla de cartas, en este metodo pasamos el 
        array bidimensional a la estructura de un array unidimensional
     */
    private Carta[] transformacionUnidimensional() { //Para mejor tratamiento para el barajado
        Carta[] c = new Carta[this.cartas.length * this.cartas[0].length];
        int indice = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                c[indice++] = cartas[i][j];
            }

        }

        return c;
    }

    /*
        Método auxiliar para la mezcla de cartas, en este metodo pasamos el 
        array unidimensional a la estructura de un array bidimensional
     */
    private void transformacionBidimensional(Carta[] cartasTratadas) {
        int indice = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                this.cartas[i][j] = cartasTratadas[indice++];
            }

        }
    }

    //Método usado para devolver una de las 4 filas cuando las barajamos 
    //para incorporarlas a las barajas de algun jugador
    public Carta[] devolverFilaCartas(int i) {
        return this.cartas[i];
    }

    //Metodo con el que podemos repintar todo el tablero 
    public void repintar() {

        //Quitamos todo el contenido del tablero
        this.removeAll();
        this.revalidate();
        this.repaint();

        //Volvemos a seleccionar la distribucion de elementos
        this.setLayout(new GridLayout(5, 13, 5, 5));
        this.setBackground(new Color(8, 100, 4));

        //Colocamos todas las cartas en el tablero
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 0; j < cartas[0].length; j++) {
                if (cartas[i][j] == null) { //Si son nulas solo mostramos el recuadro verde-oscuro
                    Carta c = new Carta();
                    c.fondoCarta("Cartes/verdeOscuro.png");
                    this.add(c);
                } else {
                    this.add(cartas[i][j]);
                }
            }
        }

        //Imprimimos las cartas del jugador
        for (int k = 0; k < cartasJugador.length; k++) {
            if (cartasJugador[k] == null) { //Si son nulas solo mostramos el recuadro verde-oscuro
                Carta c = new Carta();
                c.fondoCarta("Cartes/colorTablero.png");
                this.add(c);
            } else {

                this.add(cartasJugador[k]);
            }
        }

    }

    //Vaciamos el tablero para empezar la partida
    public void vaciarTablero() {
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 0; j < cartas[0].length; j++) {
                cartas[i][j] = null;
            }
        }
    }

    //Las cartas del jugador también se encuentran en el tablero, es por ello que insertaremos las cartas
    public void setCartasJugador(Carta[] cartasJ) {
        for (int i = 0; i < cartasJ.length; i++) {
            cartasJ[i].fondoCarta("Cartes/" + cartasJ[i].getImg());
            cartasJugador[i] = cartasJ[i];
        }
    }

    //Eliminamos todas las cartas del jugador para cuando la partida haya terminado
    public void deleteCartasJugador() {
        for (int i = 0; i < cartasJugador.length; i++) {
            cartasJugador[i] = null;
        }
    }

    //Método para añadir una carta al tablero
    public void añadirCarta(Carta carta) {
        int fila = -1;

        //Descubrimos cual es el palo de la carta, y la fila a la que tiene que ir segun el orden
        for (int i = 0; i < orden.length; i++) {
            if (orden[i].equals(carta.getTipo())) {
                fila = i;
            }
        }
        //Añadimos la carta
        Carta c = new Carta(carta.getImg(), carta.getTipo(), carta.getNumero());
        carta.fondoCarta("Cartes/colorTablero.png");
        this.cartas[fila][carta.getNumero() - 1] = c;
    }

    //Eliminamos la carta que el jugador 1 a seleccionado
    public void eliminarCartaSeleccionada(Carta c) {
        for (int i = 0; i < this.cartas.length; i++) {
            if (cartasJugador[i] != null) {
                if (this.cartasJugador[i].getNumero() == c.getNumero() && this.cartasJugador[i].getTipo().equals(c.getTipo())) {
                    this.cartasJugador[i] = null;

                }
            }
        }
    }

    //Metodo que se usa para comprobar si la carta seleccionada por el jugador se puede colocar o no
    public Carta jugadorCartaColocar(Carta c) {
        int fila = -1;
        int col = c.getNumero();

        if (c.getNumero() == 7) { //SI es un siete se puede colocar automaticamente
            return c; //devolvemos la carta que se puede colocar
        }
        
        //Descubrir palo
        for (int j = 0; j < this.orden.length; j++) {
            if (orden[j].equals(c.getTipo())) {
                fila = j;
                break;
            }
        }

        //Ahora comprobamos si se puede colocar
        if (c.getNumero() == 13 && cartas[fila][11] != null) { //colocar Final
            return c;
        } else if (c.getNumero() == 1 && cartas[fila][1] != null) { //colocar Inicio
            return c;
        }

        if ((col != 13 && col != 1)) {
            if (cartas[fila][col] != null || cartas[fila][(col - 2)] != null) { //colocar Medio
                return c;
            }
        }

        return null; //En el caso de que no se pueda devuelve null
    }

    //Metodo para los bots, se busca dentro de su baraja la primera carta que se pueda colocar.
    public Carta buscarCartaColocar(Carta[] baraja) {

        for (int i = 0; i < baraja.length; i++) {
            if (baraja[i] != null) {
                Carta c = jugadorCartaColocar(baraja[i]);
                if (c != null) {
                    return c;
                }

            }
        }

        return null; //En el caso de que no se pueda devuelve null
    }

}

