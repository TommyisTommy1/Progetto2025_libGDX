package Gioco;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import entity.Player;
import utils.Defines;

public class GamePanel extends JPanel implements Runnable{

    public GestioneTasti keyH = new GestioneTasti();
    

    public GamePanel(){
        Defines.PLAYER = new Player(Defines.GAME_PANEL, keyH);
        this.setPreferredSize(new Dimension(Defines.SCREEN_WIDTH, Defines.SCREEN_HEIGHT));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.requestFocus();
        this.setFocusable(true);
        this.setBorder(new LineBorder(Color.black, 50));
    }

    public void iniziaThreadGioco(){
        Defines.THREAD_GIOCO.start();
    }

    @Override
    public void run() {
        
        
        double intervalloVisualizzazione = 1000000000/Defines.FPS; //0.016666 secondi
        double prossimaVisualizzazione = System.nanoTime() + intervalloVisualizzazione;
        while (Defines.THREAD_GIOCO != null) {
            if (keyH.suPremuto) {
                System.err.println("w");
            }
            
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
        Defines.PLAYER.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        Defines.TILE_MANAGER.draw(g2);
        Defines.PLAYER.draw(g2);

        g2.dispose();
    }
}