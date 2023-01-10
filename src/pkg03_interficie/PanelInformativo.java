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

public class PanelInformativo extends JPanel {

    //Paneles
    private JPanel izq = new JPanel(); //Asociado al jugador 2
    private JPanel mid = new JPanel(); //Asociado al jugador 3
    private JPanel der = new JPanel(); //Asociado al jugador 4

    //Textos
    private JLabel textoIzq = new JLabel("0", SwingConstants.CENTER);
    private JLabel textoMid = new JLabel("0", SwingConstants.CENTER);
    private JLabel textoDer = new JLabel("0", SwingConstants.CENTER);

    //Colores
    private Color colorFondo = new Color(8, 100, 4);
    private Color colorPanel = new Color(8, 84, 4);

    public PanelInformativo() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 12));
        this.setBackground(colorFondo);

        //Panel Izquierda
        izq.setPreferredSize(new Dimension(75, 95));
        izq.setBackground(colorPanel);

        ImageIcon imgIcon = new ImageIcon("Cartes/card_back_blue.png");
        Image img = imgIcon.getImage(); //Hacemos una redimension
        Image newimg = img.getScaledInstance(75, 88, java.awt.Image.SCALE_DEFAULT);
        imgIcon = new ImageIcon(newimg);
        textoIzq.setIcon(imgIcon);
        textoIzq.setPreferredSize(new Dimension(75, 85));
        textoIzq.setForeground(Color.white);
        textoIzq.setHorizontalTextPosition(JLabel.CENTER);
        textoIzq.setVerticalTextPosition(JLabel.CENTER);
        textoIzq.setFont(textoIzq.getFont().deriveFont(30.0f));

        izq.add(textoIzq);

        //Panel centro
        mid.setPreferredSize(new Dimension(75, 95));
        mid.setBackground(colorPanel);

        textoMid.setIcon(imgIcon);
        textoMid.setPreferredSize(new Dimension(75, 85));
        textoMid.setForeground(Color.white);
        textoMid.setHorizontalTextPosition(JLabel.CENTER);
        textoMid.setVerticalTextPosition(JLabel.CENTER);
        textoMid.setFont(textoMid.getFont().deriveFont(30.0f));
        mid.add(textoMid);

        //Penel derecha
        der.setPreferredSize(new Dimension(75, 95));
        der.setBackground(colorPanel);

        textoDer.setIcon(imgIcon);
        textoDer.setPreferredSize(new Dimension(75, 85));
        textoDer.setForeground(Color.white);
        textoDer.setHorizontalTextPosition(JLabel.CENTER);
        textoDer.setVerticalTextPosition(JLabel.CENTER);
        textoDer.setFont(textoDer.getFont().deriveFont(30.0f));
        der.add(textoDer);

        this.add(izq);
        this.add(mid);
        this.add(der);
        this.panelesModoNormal(); //Sin imagen
    }

    //Métodos para cambiar la informacion de cada panel
    public void actualizarPanelIzq(int n) {
        textoIzq.setText(Integer.toString(n));
    }

    //Métodos para cambiar la informacion de cada panel
    public void actualizarPanelMid(int n) {
        textoMid.setText(Integer.toString(n));
    }

    //Métodos para cambiar la informacion de cada panel
    public void actualizarPanelDer(int n) {
        textoDer.setText(Integer.toString(n));
    }

    //Cundo no estamos en partida no hay fondo de contadores
    public void panelesModoNormal() {
        textoIzq.setIcon(null);
        textoMid.setIcon(null);
        textoDer.setIcon(null);
        repaint();
    }

    //Cuando estamos en partida cambia a fondos de carta los contadores
    public void panelesModoJuego() {
        ImageIcon imgIcon = new ImageIcon("Cartes/card_back_blue.png");
        Image img = imgIcon.getImage(); //Hacemos una redimension
        Image newimg = img.getScaledInstance(75, 88, java.awt.Image.SCALE_DEFAULT);
        imgIcon = new ImageIcon(newimg);
        textoIzq.setIcon(imgIcon);
        textoMid.setIcon(imgIcon);
        textoDer.setIcon(imgIcon);
        repaint();
    }
}
