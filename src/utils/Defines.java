package utils;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import Gioco.GamePanel;
import Gioco.GestioneTasti;
import entity.Player;
import tile.TileManager;
public class Defines {

    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    // VARIABILI SCHERMO 
    public static final int SCREEN_WIDTH = toolkit.getScreenSize().width;
    public static final int SCREEN_HEIGHT = toolkit.getScreenSize().height;
    public static final JFrame MAINFRAME = new JFrame("GameScreen");
    
    //VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    //VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 16;
    public static final int SCALA = 3;

    public static final int GRANDEZZA_CASELLE = GRANDEZZA_CASELLE_ORIGINALE * SCALA;
    public static final int MASSIMA_ALTEZZA_COL = 16;
    public static final int MASSIMA_ALTEZZA_RIG = 12;
    

    public static final int FPS = 60;

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static final GestioneTasti GESTIONE_TASTI  = new GestioneTasti();
    public static final Player PLAYER = new Player(GAME_PANEL, GESTIONE_TASTI);
    public static final TileManager TILE_MANAGER = new TileManager(GAME_PANEL);
}
