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

public class PanelConsola extends JPanel{
    private JLabel textoConsola = new JLabel("Has de barajar para empezar la partida",SwingConstants.LEFT);
    
    public PanelConsola(){
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        textoConsola.setFont(textoConsola.getFont().deriveFont(12.0f));
        this.add(textoConsola);
    }
    
    //Método para cambiar el texto de la consola
    public void cambiarTexto(String s){
        this.textoConsola.setText(s);
    }
}
