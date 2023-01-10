/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pkg03_interficie;

/**
 *
 * @author Daniel Salanova Dmitriyev GRUPO 2
 * Video explicativo: https://www.youtube.com/watch?v=Wm7jBxtVNL4
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {

    private PanelInformativo panelInformativo = new PanelInformativo();
    private Tablero tablero = new Tablero();
    private PanelInferior panelInferior = new PanelInferior();

    private Jugador j1 = new Jugador(); //Este es nuestro jugador
    private Jugador j2 = new Jugador();
    private Jugador j3 = new Jugador();
    private Jugador j4 = new Jugador();
    private Jugador[] jugadores = new Jugador[3]; //Array para los bots

    private int turno = 1;
    private boolean turnoJugador = true;
    private boolean isMezclado = false;

    public App() {
        super();

        //Estructura del contenedor
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        //Panel Informativo con todos los contadores de cartas de los otros jugadores
        c.add(panelInformativo, BorderLayout.NORTH);

        //Panel Tablero
        c.add(tablero, BorderLayout.CENTER);

        //Panel inferior
        c.add(panelInferior, BorderLayout.SOUTH);
        panelInferior.getBotones().getMezclar().addActionListener(new GestorBotones());
        panelInferior.getBotones().getJugar().addActionListener(new GestorBotones());
        panelInferior.getBotones().getPasar().addActionListener(new GestorBotones());
        panelInferior.getBotones().getTurnoJugador().addActionListener(new GestorBotones());
        panelInferior.getBotones().getR1().addActionListener(new GestorBotones());
        panelInferior.getBotones().getR2().addActionListener(new GestorBotones());
        panelInferior.getBotones().getR3().addActionListener(new GestorBotones());
        //Especificaciones de la ventana
        this.pack();
        this.setSize(1200, 900);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        App a = new App();

    }

    //Método que describe el inicio de una partida
    private void jugarPartida() {

        if (isMezclado) { //En caso de haber mezclado empezamos la partida
            //Repartimos las cartas
            panelInferior.getConsola().cambiarTexto("Cartas Repartidas");
            repartirCartas(j1, j2, j3, j4);

            //Añadimos nuestras cartas al tablero
            tablero.setCartasJugador(j1.getBaraja().getCartas());

            //Vaciamos todo el tablero
            tablero.vaciarTablero();

            //Repintamos el tablero de la manera correcta
            tablero.repintar();

            //Cambiaremos todos los textos tanto del panel inferior como del superior
            panelInferior.getConsola().cambiarTexto("Seleccione una carta");
            panelInformativo.panelesModoJuego();
            panelInformativo.actualizarPanelIzq(jugadores[0].getBaraja().getNumeroCartas());
            panelInformativo.actualizarPanelMid(jugadores[1].getBaraja().getNumeroCartas());
            panelInformativo.actualizarPanelDer(jugadores[2].getBaraja().getNumeroCartas());
            panelInferior.actualizarContador(j1.getBaraja().getNumeroCartas());
            panelInferior.getBotones().cambiarPantalla("p-r");
        } else {
            panelInferior.getConsola().cambiarTexto("Debe mezclar para poder jugar");
        }

    }

    //Método que describirá el turno de un bot
    private void jugarPartidaBot() {
        int posJugador = 0;

        if (turno % 4 == 2) {
            posJugador = 0;
        } else if (turno % 4 == 3) {
            posJugador = 1;
        } else if (turno % 4 == 0) {
            posJugador = 2;
        }

        Carta c = tablero.buscarCartaColocar(jugadores[posJugador].getBaraja().getCartas());

        if (c != null) {
            tablero.añadirCarta(c);

            //Eliminarla de la mano del jugador y Descontar cantidad de cartas.
            jugadores[posJugador].getBaraja().eliminarCartaSeleccionada(c);

            //repintar tablero.
            tablero.repintar();

            panelInferior.getConsola().cambiarTexto("J" + (posJugador + 2) + " ha añadido carta: " + c.getTipo() + " " + c.getNumero());

            //Cambiamos los botones
            panelInferior.getBotones().cambiarPantalla("t-r");

            if (posJugador == 0) {
                panelInformativo.actualizarPanelIzq(jugadores[0].getBaraja().getNumeroCartas());
            } else if (posJugador == 1) {
                panelInformativo.actualizarPanelMid(jugadores[1].getBaraja().getNumeroCartas());
            } else if (posJugador == 2) {
                panelInformativo.actualizarPanelDer(jugadores[2].getBaraja().getNumeroCartas());
            }

            //Ha pasado un turno
            turno++;

            if (jugadores[posJugador].getBaraja().getNumeroCartas() == 0) {
                JOptionPane.showMessageDialog(null, "Victoria del jugador " + (posJugador + 2), "Imagen victoria", JOptionPane.PLAIN_MESSAGE, jugadores[posJugador].getIcono());
                reiniciarPartida();
            }

        } else {

            panelInferior.getConsola().cambiarTexto("J" + (posJugador + 2) + " ha pasado turno");
            turno++;

        }

        if (posJugador == 2 && jugadores[2].getBaraja().getNumeroCartas() != 0) { // Sie es el jugador 4 el siguiente turno es del jugador principal
            panelInferior.getBotones().cambiarPantalla("p-r");
            turnoJugador = true;
        }
    }

    private void reiniciarPartida() {
        //Reiniciar llevar a cabo todo el reinicio del juego!

        //Devolvemos el tablero a su estado inicial
        tablero.inicializarTablero();
        tablero.deleteCartasJugador();
        panelInformativo.panelesModoNormal();
        panelInformativo.actualizarPanelIzq(0);
        panelInformativo.actualizarPanelMid(0);
        panelInformativo.actualizarPanelDer(0);
        panelInferior.actualizarContador(0);
        tablero.repintar();
        isMezclado = false;
        turnoJugador = true;
        turno = 1;
        panelInferior.getBotones().cambiarPantalla("m-j-r");
        panelInferior.getConsola().cambiarTexto("Has de barajar para empezar la partida");
    }

    private class GestorBotones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getActionCommand().equals("Mezclar")) { //Seleccionado boton mezclar

                //Barajamos las cartas
                tablero.barajarCartas();
                panelInferior.getConsola().cambiarTexto("¡La baraja esta mezclada!");
                isMezclado = true;
                tablero.repintar();

            } else if (evento.getActionCommand().equals("Jugar")) { //Seleccionado boton Jugar
                jugarPartida();

            } else if (evento.getActionCommand().equals("Reiniciar")) { //Seleccionado boton reiniciar

                reiniciarPartida();

            } else if (evento.getActionCommand().equals("Pasar")) { //J1 selecciona pasar de turno
                panelInferior.getConsola().cambiarTexto("J1 pasa");
                turnoJugador = false;
                panelInferior.getBotones().cambiarPantalla("t-r");

                //Aumentamos en 1 el turno
                turno++;

            } else if (evento.getActionCommand().equals("Turno jugador")) {
                if (turno % 4 != 1) { //No es turno del jugador 1
                    jugarPartidaBot();
                }
            }

        }
    }

    private class GestorEventoCartas implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //Recogemos la carta clickada!
            Carta c = (Carta) e.getSource();

            if (c.getNumero() == 7 && turnoJugador) { //En el caso de que sea turno del J1 y haya seleccionado un 7 se coloca inmediatamente
                jugarCarta(c);

            } else if (tablero.jugadorCartaColocar(c) != null && turnoJugador) { //En el caso de que se pueda colocar la carta se coloca
                jugarCarta(c);

            } else if (turnoJugador) { //Si es imposible colocar la carta se hace un beep

                //Beep de carta no se puede colocar
                panelInferior.getConsola().cambiarTexto("J1 no puede colocar esta carta: " + c.getTipo() + " " + c.getNumero());
                Toolkit.getDefaultToolkit().beep();
            }

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        private void jugarCarta(Carta c) {
            //Se puede colocar inmediatamente la carta
            //Colocar la carta en el tablero.
            tablero.añadirCarta(c);
           
            //Repintamos el tablero
            tablero.repintar();
            
            //Eliminarla de la mano del jugador y Descontar cantidad de cartas.
            j1.getBaraja().eliminarCartaSeleccionada(c);

            //Eliminarla de la parte gráfica del tablero.
            tablero.eliminarCartaSeleccionada(c);

            //repintar tablero.
            tablero.repintar();
            panelInferior.getConsola().cambiarTexto("J1 ha añadido carta: " + c.getTipo() + " " + c.getNumero());

            //Cambiamos los botones
            panelInferior.getBotones().cambiarPantalla("t-r");

            panelInferior.actualizarContador(j1.getBaraja().getNumeroCartas());
            //Ha pasado un turno
            turno++;

            //Evitamos que el jugador pueda colocar cartas
            turnoJugador = false;

            if (j1.getBaraja().getNumeroCartas() == 0) {
                JOptionPane.showMessageDialog(null, "Has ganado", "Imagen victoria", JOptionPane.PLAIN_MESSAGE, j1.getIcono());

                reiniciarPartida();
            }
        }

    }

    //Se reparten todas las cartas que se habían barajado, cada uno tendrá una
    //fila completa y en orden
    private void repartirCartas(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        Baraja b1 = new Baraja(tablero.devolverFilaCartas(0));
        j1.setBaraja(b1);
        j1.setIcono(tratarIcono("Jug0Riu.png"));
        //Ha todas las cartas les añadimos el gestor de eventos
        Carta[] cartasJ1 = j1.getBaraja().getCartas();
        for (int i = 0; i < cartasJ1.length; i++) {
            cartasJ1[i].addMouseListener(new GestorEventoCartas());
        }

        Baraja b2 = new Baraja(tablero.devolverFilaCartas(1));
        j2.setBaraja(b2);
        j2.setIcono(tratarIcono("Jug1Riu.png"));
        jugadores[0] = j2;

        Baraja b3 = new Baraja(tablero.devolverFilaCartas(2));
        j3.setBaraja(b3);
        j3.setIcono(tratarIcono("Jug2Riu.png"));
        jugadores[1] = j3;

        Baraja b4 = new Baraja(tablero.devolverFilaCartas(3));
        j4.setBaraja(b4);
        j4.setIcono(tratarIcono("Jug3Riu.png"));
        jugadores[2] = j4;
    }

    private Icon tratarIcono(String img) {
        ImageIcon imgIcon = new ImageIcon("Cartes/" + img);
        Image imgN = imgIcon.getImage(); //Hacemos una redimension
        Image newimg = imgN.getScaledInstance(85, 105, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }
}
