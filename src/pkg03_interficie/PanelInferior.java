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

public class PanelInferior extends JPanel {

    private JPanel panelContador = new JPanel();
    private JLabel contadorJugador = new JLabel("0", SwingConstants.LEFT);
    private PanelConsola consola = new PanelConsola();
    private PanelBotones botones = new PanelBotones();

    //Panel con todo el contenido de la parte inferior de la ventana
    public PanelInferior() {
        super();
        this.setLayout(new GridLayout(3, 1));

        panelContador.setLayout(new FlowLayout(FlowLayout.LEFT));
        contadorJugador.setFont(contadorJugador.getFont().deriveFont(22.0f));
        panelContador.add(contadorJugador);
        this.add(panelContador);
        this.add(botones);
        this.add(consola);
    }

    public PanelConsola getConsola() {
        return consola;
    }

    public PanelBotones getBotones() {
        return botones;
    }

    public void actualizarContador(int n) {
        contadorJugador.setText(Integer.toString(n));
    }

}
