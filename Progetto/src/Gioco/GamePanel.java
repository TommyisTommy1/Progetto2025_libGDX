package Gioco;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

    //SCHERMO
    final int grandezzaCaselleOriginale = 16; //grandezza di un solo oggetto
    final int scala = 3;

    public final int grandezzaCaselle = grandezzaCaselleOriginale * scala;
    final int massimaAltezzaCol=16;
    final int massimaAltezzaRig=12;
    final int larghezzaSchermo = grandezzaCaselle*massimaAltezzaCol;
    final int altezzaSchermo = grandezzaCaselle*massimaAltezzaRig;

    GestioneTasti gestioneTasti = new GestioneTasti();
    Thread threadGioco;
    

    int FPS = 60;

    Player player = new Player(this,gestioneTasti);

    int playerX = 500;
    int playerY = 500;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(larghezzaSchermo, altezzaSchermo));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(gestioneTasti);
        this.setFocusable(true);
    }

    public void iniziaThreadGioco(){
        threadGioco = new Thread(this);
        threadGioco.start();
    }

    @Override
    public void run() {
        
        
        double intervalloVisualizzazione = 1000000000/FPS; //0.016666 secondi
        double prossimaVisualizzazione = System.nanoTime() + intervalloVisualizzazione;
        while (threadGioco != null) {

            update();

            repaint();
            
            
            
            
            try {
                double tempoRimanente = prossimaVisualizzazione  - System.nanoTime();
                tempoRimanente = tempoRimanente/1000000;
                if (tempoRimanente<0) {
                    tempoRimanente=0;
                }
                Thread.sleep((long) tempoRimanente);

                prossimaVisualizzazione+=intervalloVisualizzazione;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}