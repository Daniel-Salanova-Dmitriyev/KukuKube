
package pkg02_interficie;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfo extends JPanel {
    
    //Esta clase se hará cargo del panel informativo, que contendrá 8 etiquetas, 4 de texto y 4 que contendrán números.
    //Por último habrá un método de acutalización de valores para que se actualice el valor de estos datos en el panel

    private JLabel nivelPartida, nivelPartidaN, nivelActual, nivelActualN, nivelesRestantes, nivelesRestantesN, puntuacion, puntuacionN;
    private Integer nPart, nAct, nRest, nPunt;

    public PanelInfo() {
        this.setLayout(null);

        this.setBackground(Color.BLACK);
        nivelPartida = new JLabel("NIVELES PARTIDA");
        nivelPartida.setOpaque(true);
        nivelPartida.setBackground(Color.white);
        nivelPartida.setBounds(5, 5, 200, 30);
        nivelPartida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelPartida.setHorizontalAlignment(JLabel.CENTER);

        nivelActual = new JLabel("NIVEL ACTUAL");
        nivelActual.setOpaque(true);
        nivelActual.setBackground(Color.white);
        nivelActual.setBounds(5, 40, 200, 30);
        nivelActual.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelActual.setHorizontalAlignment(JLabel.CENTER);

        nivelPartidaN = new JLabel("000");
        nivelPartidaN.setOpaque(true);
        nivelPartidaN.setBackground(Color.white);
        nivelPartidaN.setBounds(210, 5, 40, 30);
        nivelPartidaN.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelPartidaN.setHorizontalAlignment(JLabel.CENTER);
        nivelPartidaN.setForeground(Color.red);

        nivelActualN = new JLabel("000");
        nivelActualN.setOpaque(true);
        nivelActualN.setBackground(Color.white);
        nivelActualN.setBounds(210, 40, 40, 30);
        nivelActualN.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelActualN.setHorizontalAlignment(JLabel.CENTER);
        nivelActualN.setForeground(Color.red);

        nivelesRestantes = new JLabel("NIVELES RESTANTES");
        nivelesRestantes.setOpaque(true);
        nivelesRestantes.setBackground(Color.white);
        nivelesRestantes.setBounds(330, 5, 220, 30);
        nivelesRestantes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelesRestantes.setHorizontalAlignment(JLabel.CENTER);

        puntuacion = new JLabel("PUNTUACIÓN");
        puntuacion.setOpaque(true);
        puntuacion.setBackground(Color.white);
        puntuacion.setBounds(330, 40, 220, 30);
        puntuacion.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        puntuacion.setHorizontalAlignment(JLabel.CENTER);

        nivelesRestantesN = new JLabel("000");
        nivelesRestantesN.setOpaque(true);
        nivelesRestantesN.setBackground(Color.white);
        nivelesRestantesN.setBounds(555, 5, 40, 30);
        nivelesRestantesN.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        nivelesRestantesN.setHorizontalAlignment(JLabel.CENTER);
        nivelesRestantesN.setForeground(Color.red);

        puntuacionN = new JLabel("000");
        puntuacionN.setOpaque(true);
        puntuacionN.setBackground(Color.white);
        puntuacionN.setBounds(555, 40, 40, 30);
        puntuacionN.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        puntuacionN.setHorizontalAlignment(JLabel.CENTER);
        puntuacionN.setForeground(Color.red);

        this.add(nivelPartida);
        this.add(nivelActual);
        this.add(nivelPartidaN);
        this.add(nivelActualN);
        this.add(nivelesRestantes);
        this.add(puntuacion);
        this.add(nivelesRestantesN);
        this.add(puntuacionN);
    }

    public void actualizarValores(int nPart, int nAct, int nRest, int nPunt) {
        this.nPart = nPart;
        this.nAct = nAct;
        this.nRest = nRest;
        this.nPunt = nPunt;

        String res = Integer.toString(nPart);
        if (res.length() == 1) {
            nivelPartidaN.setText("00" + res);
        } else {
            nivelPartidaN.setText("0" + res);

        }
        
        res = Integer.toString(nAct);
        if (res.length() == 1) {
            nivelActualN.setText("00" + res);
        } else {
            nivelActualN.setText("0" + res);

        }
        
        res = Integer.toString(nRest);
        if (res.length() == 1) {
            nivelesRestantesN.setText("00" + res);
        } else {
            nivelesRestantesN.setText("0" + res);

        }
        
        res = Integer.toString(nPunt);
        if (res.length() == 1) {
            puntuacionN.setText("00" + res);
        } else {
            puntuacionN.setText("0" + res);

        }
        
        this.repaint();
        
        
    }

}
