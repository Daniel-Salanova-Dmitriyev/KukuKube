/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg02_interficie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author raulalcauter
 */
public class Tablero extends JPanel {

    //Esta clase está basado en un tablero, este a su vez se bassa en un array bidimensional de casillas o cuadrados, cada una con su
    //lado, color, color diferencial. También tendrá valores como las componentes del cuadrado diferencial, del seleccionado, y un
    //booleano que dirá si hay que pintar la corrección en el tablero.
    private final int maxPixeles = 600;
    private Color color;
    float lado;
    private int R, G, B;
    private Cuadrado[][] c;
    private int xd, yd, comp, complej, ix, jx;
    private boolean pintarY = false;

    public Tablero(int n, int xd, int yd, int comp) {
        //En el constructor se inicializarán los cuadrados de el tablero, y los colores en conjunto con su diferencial que lo dirá 
        //la complejidad de la partida.
        this.comp = comp;
        this.xd = xd;
        this.yd = yd;
        //Se calcula el lado del cuadrado en el tablero
        lado = maxPixeles / n;
        c = new Cuadrado[n][n];
        Random ran = new Random();
        //Se calculan las componentes R G B del color principal, posteriormente se calculará el color diferencial sumandole a las
        //componentes el valor 'complej' calculado soabiendo la complejidad de la partida
        R = ran.nextInt(186);
        G = ran.nextInt(186);
        B = ran.nextInt(186);
        color = new Color(R, G, B);
        complej = sacarComplejidad(comp);
        
        //En estos dos bucles anidados irán creándose cada cuadrado del tablero, si la posición i coincide con xd y la j con yd
        //Se llevará a cabo la creación del cuadrado diferencial
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Cada cuadrado será un Rectangle2D.Float que estará posicionado en i*lado , j*lado
                //y sus diemsniones serán de lado x lado
                Rectangle2D.Float r = new Rectangle2D.Float(i * lado, j * lado, lado, lado);

                if (xd == i && yd == j) {
                    Color colorDif = new Color(R + complej, G + complej, B + complej);
                    c[i][j] = new Cuadrado(true, colorDif, r, lado);
                } else {
                    c[i][j] = new Cuadrado(false, color, r, lado);
                }
            }

        }

    }
    
    //En este método se irán pintando cada cuadrado formando el tablero, si la variable pintarY es verdadera, entonces se pintará el
    //tablero con la corrección correspondiente
    @Override
    public void paintComponent(Graphics g) {
        
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                c[i][j].paintComponent(g);
            }
        }
        if(pintarY){
            pintarCorreccion(g, c[ix][jx].getCuadrado(), false);
            pintarCorreccion(g, c[xd][yd].getCuadrado(), true);
        }
        
    }
    
    //Este método es llamado al pintarse el tablero si se debe pintar la corrección y se encargará de hacer dicha acción
    //entre sus parámetros destacamos los gráficos para pintar la corrección, el rectángulo a pintar y un booleano que dirá 
    //si se debe enmarcar el cuadrado correcto o el erroneo con su respectivo color
    private void pintarCorreccion(Graphics g, Rectangle2D.Float rec, boolean b) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        
        if (!b) {
            g2d.setColor(Color.red);
            g2d.draw(rec);
        } else {
            g2d.setColor(Color.green);
            g2d.draw(rec);
        }

    }
    
    //Este método preparará al booleano que dirá si se debe pintar el tablero con la corrección
    public void setPintarCorreccion(){
        pintarY = true;
    }

    //Este método comprueba si el cuadrado seleccionado es el correcto o es el erróneo, en cualquier caso, se guardarán las componentes 
    //del cuadrado seleccionado por el usuario
    public boolean comprobarDif(int i, int j) {
        this.ix = i;
        this.jx = j;
        return c[i][j].isDifferent();
    }

    //Este método devolverá un entero que se deberá sumar al valor R, G y B del color principal para sacar el color diferencial.
    //La funcionalidad de este método se basará en comparar el valor entrado de complejidad por el usuario y devolverá un número más
    //alto al tratarse de una complejidad baja y un número más bajo en el caso contrario.
    public int sacarComplejidad(int comp) {
        switch (comp) {
            case 10:
                return 10;
            case 9:
                return 15;
            case 8:
                return 20;
            case 7:
                return 25;
            case 6:
                return 30;
            case 5:
                return 35;
            case 4:
                return 40;
            case 3:
                return 50;
            case 2:
                return 60;
            case 1:
                return 70;
            default:
                return 50;
        }

    }

}
