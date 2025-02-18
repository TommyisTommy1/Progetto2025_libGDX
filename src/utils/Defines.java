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
    // VARIABILI SCHERMO 
    public static final int SCREEN_WIDTH = (int)toolkit.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int)toolkit.getScreenSize().getHeight();
    public static final JFrame MAINFRAME = new JFrame("GameScreen");
    public static int NUM_COLONNE = 50; // modificabile per cambiare la dimensione della visuale
    public static int NUM_RIGHE = 50; // si modifica a seconda della prima per avere la risuluzione in 16:9

    // VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 32;
    public static final double SCALA = 4;

    public static final int GRANDEZZA_CASELLE = (int) Math .ceil(GRANDEZZA_CASELLE_ORIGINALE * SCALA); //L'ultimo numero è il moltiplicatore della telecamera
    
    // MP3 AUDIO CONTROL
    public static final MP3Player CIRCUSTHEME_CLASS = new MP3Player(0);
    public static final Thread CIRCUSTHEME_PLAYER = new Thread(CIRCUSTHEME_CLASS);
    public static final MP3Player COMBATTHEME_CLASS = new MP3Player(1);
    public static final Thread COMBATTHEME_PLAYER = new Thread(COMBATTHEME_CLASS);
    public static final MP3Player DARKTHEME_CLASS = new MP3Player(2);
    public static final Thread DARKTHEME_PLAYER = new Thread(DARKTHEME_CLASS);
    public static final MP3Player HOSTILETHEME_CLASS = new MP3Player(3);
    public static final Thread HOSTILETHEME_PLAYER = new Thread(HOSTILETHEME_CLASS);
    public static final MP3Player WINTERTHEME_CLASS = new MP3Player(4);
    public static final Thread WINTERTHEME_PLAYER = new Thread(WINTERTHEME_CLASS);

    


    // VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    

    
    public static final int FPS = 60;

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static final Player PLAYER = new Player(GamePanel.keyH);
    public static final TileManager TILE_MANAGER = new TileManager();
}
