package pkg03_interficie;

/**
 *
 * @author Daniel Salanova Dmitriyev GRUPO 2
 * Video explicativo: https://www.youtube.com/watch?v=Wm7jBxtVNL4
 */
public class Baraja {

    private Carta[] cartas = new Carta[13]; //Cartas dentro del mazo del jugador
    private int numeroCartas = -1; //Contador con el total de cartas

    public Baraja(Carta[] cartasJ) {
        for (int i = 0; i < cartasJ.length; i++) { //Colocamos el array de cartas a la baraja

            //Es necesario incorporlo de la siguiente manera, ya que una vez que empecemos la partida
            //cada una de las cartas que hemos incorporado pasarán a null dentro del tablero, sino lo hicieramos
            //así estas cartas incorporadas dentro de la baraja también pasarían a null
            Carta c = new Carta(cartasJ[i].getImg(), cartasJ[i].getTipo(), cartasJ[i].getNumero());

            this.cartas[i] = c;
        }
        numeroCartas = cartas.length; //Siempre 13 para este juego
    }

    //Eliminamos la carta de la baraja
    public void eliminarCartaSeleccionada(Carta carta) {
        for (int i = 0; i < this.cartas.length; i++) {
            if (this.cartas[i] != null) {
                if (this.cartas[i].getNumero() == carta.getNumero() && this.cartas[i].getTipo().equals(carta.getTipo())) {
                    this.cartas[i] = null;
                    this.numeroCartas -= 1;
                }
            }
        }
    }

    public Carta[] getCartas() {
        return this.cartas;
    }

    public int getNumeroCartas() {
        return this.numeroCartas;
    }

}
