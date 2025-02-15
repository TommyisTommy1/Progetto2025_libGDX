package utils;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import Gioco.GamePanel;
import entity.Player;
import tile.TileManager;
public class Defines {

    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    // VARIABILI SCHERMO 
    public static final int SCREEN_WIDTH = (int)toolkit.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int)toolkit.getScreenSize().getHeight();
    public static final JFrame MAINFRAME = new JFrame("GameScreen");
    
    //VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    //VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 16;
    public static final int SCALA = 3;

    public static final int GRANDEZZA_CASELLE = GRANDEZZA_CASELLE_ORIGINALE * SCALA;
    public static final int MASSIMA_ALTEZZA_COL = ((int)toolkit.getScreenSize().getWidth())/GRANDEZZA_CASELLE; 
    public static final int MASSIMA_ALTEZZA_RIG = ((int)toolkit.getScreenSize().getWidth())/GRANDEZZA_CASELLE;
    

    public static final int FPS = 60;

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static Player PLAYER;
    public static TileManager TILE_MANAGER = new TileManager(GAME_PANEL);
}
