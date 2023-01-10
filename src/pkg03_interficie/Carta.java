/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg03_interficie;

/**
 *
 * @author Daniel Salanova Dmitriyev GRUPO 2
 * video explicativo: https://www.youtube.com/watch?v=Wm7jBxtVNL4
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Carta extends JLabel {

    private String img; //Dirección de la imagen
    private String tipo; //Tipo de carta: spades, hearts...
    private int numero; //numero de la carta

    public Carta() {
        super();
    }

    public Carta(String img, String tipo, int numero) {
        super();
        this.img = img;
        this.tipo = tipo;
        this.numero = numero;

        //Redimensionamos la imagen añadida con esta carta
        ImageIcon imgIcon = new ImageIcon("Cartes/" + img);
        Image imgN = imgIcon.getImage(); //Hacemos una redimension
        Image newimg = imgN.getScaledInstance(85, 105, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        this.setIcon(imgIcon);
    }

    public String getImg() {
        return img;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }



    public void fondoCarta(String img) { //Estará en tablero sin imagen de carta
        this.setBackground(null);
        ImageIcon imgIcon = new ImageIcon(img);//Imagen añadida al proyecto para la sombra de la carta
        Image imgN = imgIcon.getImage(); //Hacemos una redimension
        Image newimg = imgN.getScaledInstance(85, 105, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        this.setIcon(imgIcon);
        repaint();
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Carta{" + "img=" + img + ", tipo=" + tipo + ", numero=" + numero + '}';
    }

}
