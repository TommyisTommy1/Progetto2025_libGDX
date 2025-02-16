package utils;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import Gioco.GamePanel;
import entity.Player;
import tile.TileManager;
import mp3PlayerPkg.MP3Player;

public class Defines {

    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    // MP3 AUDIO CONTROL
    public static final MP3Player AUDIO_PLAYER = new MP3Player();
    public static final Thread MP3_PLAYER = new Thread(AUDIO_PLAYER);
    // VARIABILI SCHERMO 
    public static final int SCREEN_WIDTH = 1920; //(int)toolkit.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = 1080; //(int)toolkit.getScreenSize().getHeight();
    public static final JFrame MAINFRAME = new JFrame("GameScreen");
    
    //VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    public static final int MASSIMA_ALTEZZA_COL = 15; //modificabile per cambiare la dimensione della mappa
    public static final int MASSIMA_ALTEZZA_RIG = (int) Math.ceil((double) MASSIMA_ALTEZZA_COL/1.778) ; //si modifica a seconda della prima per avere la risuluzione in 16:9
    //VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 16;
    public static final int SCALA = (int) Math.ceil(((double) SCREEN_WIDTH/(GRANDEZZA_CASELLE_ORIGINALE*MASSIMA_ALTEZZA_COL)));

    public static final int GRANDEZZA_CASELLE = GRANDEZZA_CASELLE_ORIGINALE * SCALA;

    public static final int FPS = 60;

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static final Player PLAYER = new Player(GAME_PANEL, GamePanel.keyH);
    public static final TileManager TILE_MANAGER = new TileManager(GAME_PANEL);
}
