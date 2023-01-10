/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

public class PanelBotones extends JPanel {

    private CardLayout card = new CardLayout();

    private JButton mezclar = new JButton("Mezclar");
    private JButton pasar = new JButton("Pasar");
    private JButton turnoJugador = new JButton("Turno jugador");
    private JButton jugar = new JButton("Jugar");

    //Los tres botones reiniciar para cada una de las cards
    private JButton r1 = new JButton("Reiniciar");
    private JButton r2 = new JButton("Reiniciar");
    private JButton r3 = new JButton("Reiniciar");

    private String estado = "";

    public PanelBotones() {
        super();
        this.setLayout(card); //Card Layout

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p1.add(mezclar);
        p1.add(jugar);
        p1.add(r1);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p2.add(turnoJugador);
        p2.add(r2);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p3.add(pasar);
        p3.add(r3);

        this.add(p2, "t-r");
        this.add(p3, "p-r");
        this.add(p1, "m-j-r");
        estado = "m-j-r";
        card.show(this, "m-j-r");

    }

    //Método que sirve para cambiar entre las distintas cards que tiene el layout
    public void cambiarPantalla(String s) {
        if (s.equals("m-j-r")) {
            estado = "m-j-r";
            card.show(this, estado);
        } else if (s.equals("t-r")) {
            estado = "t-r";
            card.show(this, estado);

        } else if (s.equals("p-r")) {
            estado = "p-r";
            card.show(this, estado);
        }
    }

    public JButton getMezclar() {
        return mezclar;
    }

    public JButton getPasar() {
        return pasar;
    }

    public JButton getTurnoJugador() {
        return turnoJugador;
    }

    public JButton getJugar() {
        return jugar;
    }

    public JButton getR1() {
        return r1;
    }

    public JButton getR2() {
        return r2;
    }

    public JButton getR3() {
        return r3;
    }

}
