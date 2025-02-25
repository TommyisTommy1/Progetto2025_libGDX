package game;

import entity.Nemico;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import utils.Defines;

public class GamePanel extends JPanel implements Runnable {

    public static GestioneTasti keyH = new GestioneTasti(); // KeyHandler
    public RilevatoreCollisioni collisioni = new RilevatoreCollisioni();
    private static int maxWorldCol;
    private static int maxWorldRow;
    private static int worldWidth;
    private static int worldHeight;

    // Getters
    public static int getMaxWorldCol() {
        return maxWorldCol;
    }

    public static int getMaxWorldRow() {
        return maxWorldRow;
    }

    public static int getWorldWidth() {
        return worldWidth;
    }

    public static int getWorldHeight() {
        return worldHeight;
    }

    // Setters
    public void setMaxWorld(int maxWorldCol, int maxWorldRow) {
        GamePanel.maxWorldCol = maxWorldCol;
        GamePanel.maxWorldRow = maxWorldRow;
        this.worldWidth = Defines.GRANDEZZA_CASELLE * GamePanel.maxWorldCol;
        this.worldHeight = Defines.GRANDEZZA_CASELLE * GamePanel.maxWorldRow;
    }

    public void setMaxWorldCol(int maxWorldCol) {
        GamePanel.maxWorldCol = maxWorldCol;
        this.worldWidth = Defines.GRANDEZZA_CASELLE * GamePanel.maxWorldCol;
    }

   

    public void setMaxWorldRow(int maxWorldRow) {
        GamePanel.maxWorldRow = maxWorldRow;
        this.worldHeight = Defines.GRANDEZZA_CASELLE * GamePanel.maxWorldCol;
    }

    // Costruttore
    public GamePanel() {
        this.setPreferredSize(new Dimension(Defines.SCREEN_WIDTH, Defines.SCREEN_HEIGHT));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.requestFocus();
        this.setFocusable(true);
    }

    // funzione per iniziare il thread del gioco
    public void iniziaThreadGioco() {
        Defines.THREAD_GIOCO.start();
    }

    // funzione per runnare il gioco
    @Override
    public void run() {

        double intervalloVisualizzazione = 1000000000 / Defines.FPS; // 0.016666 secondi
        double prossimaVisualizzazione = System.nanoTime() + intervalloVisualizzazione; //calcolo quando visualizzare il prossimo frame
        while (Defines.THREAD_GIOCO != null) {
            Defines.toolkit.sync();
            this.update(); // funzione per aggiornare il gioco
            this.repaint(); // funzione per ridisegnare il gioco

            try {
                double tempoRimanente = prossimaVisualizzazione - System.nanoTime();
                tempoRimanente = tempoRimanente / 1000000;

                if (tempoRimanente < 0)
                    tempoRimanente = 0;

                Thread.sleep((long) tempoRimanente); // aspetto il tempo rimanente per visualizzare il prossimo frame

                prossimaVisualizzazione += intervalloVisualizzazione; //calcolo quando visualizzare il prossimo frame

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // funzione per aggiornare il player
    public void update() {
        Defines.PLAYER.update(); 
        Defines.CAMERA.update();
    }

    // funzione per ridisegnare il gioco
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Defines.TILE_MANAGER.draw(g2);
        Defines.PLAYER.draw(g2);

        Nemico n = new Nemico();
        n.draw(g2);

        g2.dispose();
    }
    
}