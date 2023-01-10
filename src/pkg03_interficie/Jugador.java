package pkg03_interficie;

import javax.swing.Icon;

/**
 *
 * @author Daniel Salanova Dmitriyev GRUPO 2
 * Video explicativo: https://www.youtube.com/watch?v=Wm7jBxtVNL4s
 */

public class Jugador {
    private Baraja baraja;
    private Icon icono;
    
    public Jugador(){
        
    }
    
    public Jugador (Baraja baraja) {
        this.baraja = baraja;
    }
    
    //Recogemos la baraja del jugador
    public Baraja getBaraja() {
        return baraja;
    }

    //Le añadimos una baraja al jugador
    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }
    
    public void setIcono(Icon i){
        this.icono = i;
    }
    
    public Icon getIcono(){
        return this.icono;
    }
    
}
