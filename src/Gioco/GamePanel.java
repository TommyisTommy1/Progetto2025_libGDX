package Gioco;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import utils.Defines;

public class GamePanel extends JPanel implements Runnable{

    public final static GestioneTasti keyH = new GestioneTasti(); //KeyHandler

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = Defines.GRANDEZZA_CASELLE *  maxWorldCol;
    public final int worldHeight = Defines.GRANDEZZA_CASELLE *  maxWorldRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(Defines.SCREEN_WIDTH, Defines.SCREEN_HEIGHT));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); 
        this.requestFocus();
        this.setFocusable(true);
    }

    public void iniziaThreadGioco(){
        Defines.THREAD_GIOCO.start();
    }

    @Override
    public void run() {
        
        double intervalloVisualizzazione = 1000000000/Defines.FPS; //0.016666 secondi
        double prossimaVisualizzazione = System.nanoTime() + intervalloVisualizzazione;
        while (Defines.THREAD_GIOCO != null) {

            update();
            repaint();
            
            try {
                double tempoRimanente = prossimaVisualizzazione  - System.nanoTime();
                tempoRimanente = tempoRimanente/1000000;
                
                if (tempoRimanente<0) tempoRimanente=0;

                Thread.sleep((long) tempoRimanente);

                prossimaVisualizzazione+=intervalloVisualizzazione;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        Defines.PLAYER.update(); //funzione per aggiornare il player
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        Defines.TILE_MANAGER.draw(g2);  
        Defines.PLAYER.draw(g2);

        g2.dispose();
    }
}