
package pkg02_interficie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author raulalcauter
 */

//Esta clase hará función de casilla del tablero, la cual es un cuadrado.
//Cada cuadrado tendrá un valor booleano que determina si es el cuadrado diferencial o no, un color, un rectangulo, y el lado de
//cada cuadrado
public class Cuadrado {
    private boolean isDifferent;
    private Color color;
    private Rectangle2D.Float cuadrado;
    private float lado;

    public Cuadrado(boolean isDifferent, Color color, Rectangle2D.Float cuadrado, float lado) {
        this.isDifferent = isDifferent;
        this.color = color;
        this.cuadrado = cuadrado;
        this.lado = lado;
    }
    
    //Este método se encargará de pintar cada cuadrado enmarcado de un borde blanco
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(cuadrado);
        
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4));
        g2d.draw(cuadrado);
        
    }
    
    //Este método devuelve el Rectangle2D.Float del cuadrado.
    public Rectangle2D.Float getCuadrado(){
        return cuadrado;
    }
    
    //Este método determina si el cuadrado en cuestión es el diferncial o no.
    public boolean isDifferent(){
        return isDifferent;
    }
}
