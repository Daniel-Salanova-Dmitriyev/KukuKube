package pkg02_interficie;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterficieGrafica extends JFrame {
    
    
    //En esta clase irá el flujo del programa, desde el menú principal, hasta el juego en si.
    //Se crea una imagen tipo ImageIcon para el gif del menú principal, un panel donde irán los contenidos del programa, un JLable que 
    //contendrá la imagen del menú, la dimensión de cada tablero 'n' que a su vez podremos saber en que nivel estamos con ésta,
    //unas variables enteras que servirán de entrada del usuario para saber cuantos niveles se jugarán y que complejidad se debe
    //presentar, los puntos acumulados y un panel informativo.
    private ImageIcon gifUIB = new ImageIcon("GifUib.gif");
    private JPanel panelContenidos = new JPanel();
    private JLabel gifMenu;
    private static int n = 2;
    private Integer nivel, compl;
    private int puntos;
    private PanelInfo pInfo;

    public InterficieGrafica() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        //Creación de la ventana y el panel principal que contiene los conntenidos del juego
        setTitle("Kuku Kube");
        setBounds(400, 50, 600, 750);
        setResizable(false);

        this.getContentPane().add(panelContenidos);
        panelContenidos.setLayout(null);

        //Creación de la etiqueta del gif del menu basado en el gif de la UIB
        //Posteriormente el reescalado del gif
        gifMenu = new JLabel();
        gifMenu.setOpaque(true);
        gifMenu.setBounds(0, 75, 600, 600);
        Image img = gifUIB.getImage();
        Image imgScaled = img.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        gifUIB = new ImageIcon(imgScaled);
        gifMenu.setIcon(gifUIB);
        panelContenidos.add(gifMenu);
        
        //Creación del botón de jugar una nueva partida
        JButton nuevaPartida = new JButton();
        nuevaPartida.setText("NUEVA PARTIDA");
        nuevaPartida.setMnemonic('N');
        nuevaPartida.setBounds(0, 675, 300, 25);
        nuevaPartida.setOpaque(true);
        nuevaPartida.setBackground(Color.black);
        panelContenidos.add(nuevaPartida);
        nuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonPartida(evt);
            }
        });

        //Creación del botón de salir del juego
        JButton salir = new JButton();
        salir.setText("SALIR");
        salir.setMnemonic('S');
        salir.setBounds(300, 675, 300, 25);
        salir.setOpaque(true);
        salir.setBackground(Color.black);
        panelContenidos.add(salir);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonSalir(evt);
            }
        });
        
        //Creación del menú superior con los botones anteriores
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem nPart = new JMenuItem("Nueva Partida");
        JMenuItem salirP = new JMenuItem("Salir");
        panelContenidos.add(bar);
        bar.add(menu);
        menu.add(nPart);
        menu.add(salirP);

        nPart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonPartida(evt);
            }
        });

        salirP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        setJMenuBar(bar);
        
        //Creacion del panel informativo con ayuda de la clase PanelInfo que es un Panel JPanel
        pInfo = new PanelInfo();
        pInfo.setBounds(0, 0, 600, 73);
        panelContenidos.add(pInfo);

    }
    
    //Accion del evento sair del juego
    private void botonSalir(ActionEvent evt) {
        System.exit(0);
    }
    
    //Accion del evento de nueva partida
    private void botonPartida(ActionEvent evt) {
        if (!gifMenu.isVisible()) { //No se va a poder empezar una nueva partida si el gif ddel menu no es visible
            JOptionPane.showMessageDialog(null, "¡No puedes empezar otra partida en una partida en curso!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Creacion de las ventanas emergentes que elige las opciones de la partida
            puntos = 0;
            JOptionPane ajustesP = new JOptionPane();
            ajustesP.setVisible(true);
            ajustesP.setBounds(550, 300, 300, 250);
            //A partir de aquí revisa si el dato ingresado por el usuario es válido para ser usado, 
            //en caso contrario se le avisa al jugador de que el dato no es válido
            String nivels = ajustesP.showInputDialog(null, "Niveles (1 - 10): ", "", JOptionPane.QUESTION_MESSAGE);
            boolean flag = true;
            try {
                nivel = Integer.parseInt(nivels);
                if (!(nivel >= 1 && nivel <= 10)) {
                    JOptionPane.showMessageDialog(null, "Introduce un número válido", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = false;
                }

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Introduce un número válido", "Message", JOptionPane.INFORMATION_MESSAGE);
                flag = false;
            }
            if (flag) {
                JOptionPane ajustesP2 = new JOptionPane();
                ajustesP.setVisible(true);
                ajustesP.setBounds(550, 300, 300, 250);
                String complS = ajustesP2.showInputDialog(null, "Complejidad (1 - 10): ", "", JOptionPane.QUESTION_MESSAGE);

                try {
                    compl = Integer.parseInt(complS);
                    if (!(compl >= 1 && compl <= 10)) {
                        JOptionPane.showMessageDialog(null, "Introduce un número válido", "Message", JOptionPane.INFORMATION_MESSAGE);
                        flag = false;
                    }

                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Introduce un número válido", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = false;
                }
                if (flag) {
                    //Si todo ha ido bien, entonces se creará una partida con los datos del usuario y se iniciarán los valores 
                    //del panel informativo
                    n = 2;
                    pInfo.actualizarValores(nivel, n - 1, (nivel + 1) - n, puntos);
                    partida(true, compl);
                }
            }
        }

    }

    private void partida(boolean b, int compl) {

        if (!gifMenu.isVisible() && b) {
            JOptionPane.showMessageDialog(null, "¡No puedes empezar otra partida en una partida en curso!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Antes de empezar se creará aleatoriamente el valor x e y de la casilla que contendrá el color diferencial
            Random ran = new Random();
            int xd = ran.nextInt(n);
            int yd = ran.nextInt(n);
            //El lado de la casilla 
            float lado = 600 / n;
            
            //se crea un nuevo tablero de dimension n, casilla diferencial[xd][yd] y la complejidad introducida por el usuario
            Tablero t = new Tablero(n, xd, yd, compl);
            t.setBounds(0, 75, 600, 600);
            //Se quita la visibilidad del gif del menú principal
            gifMenu.setVisible(false);
            //Añadimos el tablero al panel de contenidos
            panelContenidos.add(t);
            t.setVisible(true);
            t.setLayout(null);

            //Añadimos un escuchador de ratón para saber donde hace click el usuario
            t.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
                
                //Cuando se presione el click guardaremos el valor x e y de la casilla
                @Override
                public void mousePressed(MouseEvent e) {
                    int x = e.getX() / (int) lado;
                    int y = e.getY() / (int) lado;
                    
                    //Se comprueba si la casilla seleccionada es la diferencial, en ese caso, se sumarán los puntos, la dimensión
                    //del siguiente tablero aumentará y se actualizarán los datos del panel informativo
                    boolean diferente = t.comprobarDif(x, y);
                    if (!diferente) {
                        //En caso contrario se pintará la corrección de la casilla seleccionada y se mostrará un aviso en el que 
                        //se transmite al usuario de que ha seleccionado mal la casilla
                        t.setPintarCorreccion();
                        t.repaint();

                        JOptionPane.showMessageDialog(null, "¡Cuadrado seleccionado erróneo!", "Message", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        puntos += ((n - 1) + 1);
                    }
                    
                    //En cualquiera de los dos casos se aumentará la dimensión del siguiente tablero y se actualizará el panel informativo
                    if (n <= nivel) {
                        pInfo.actualizarValores(nivel, (++n) - 1, (nivel + 1)- n, puntos);

                        t.setVisible(false);
                        partida(false, compl);
                    } else {
                        //Cuando la partida acabe se mostrará al usuario cuantos puntos ha conseguido y se le enviará al menú principal
                        //haciendo visible el gif del menú
                        pInfo.actualizarValores(nivel, n - 1, 0, puntos);

                        n = 2;
                        JOptionPane.showMessageDialog(null, "Partida terminada con " + puntos + " puntos", "Message", JOptionPane.INFORMATION_MESSAGE);
                        pInfo.actualizarValores(0, 0, 0, 0);
                        t.setVisible(false);
                        gifMenu.setVisible(true);
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }

}
