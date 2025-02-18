package utils;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import entity.Player;
import Gioco.GamePanel;
import tile.TileManager;
import mp3PlayerPkg.MP3Player;

public class Defines {
    public static final int SCREEN_WIDTH = 854; // (int)toolkit.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = 480; // (int)toolkit.getScreenSize().getHeight();

    // VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 32;
    public static final int SCALA = 3;

    public static final int GRANDEZZA_CASELLE = (int) Math .ceil(GRANDEZZA_CASELLE_ORIGINALE * SCALA); //L'ultimo numero è il moltiplicatore della telecamera


    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    // MP3 AUDIO CONTROL
    public static final MP3Player AUDIO_PLAYER = new MP3Player(0);
    public static final Thread MP3_PLAYER = new Thread(AUDIO_PLAYER);
    // VARIABILI SCHERMO
    
    public static final JFrame MAINFRAME = new JFrame("GameScreen");

    // VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    public static final int FPS = 60;

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static final Player PLAYER = new Player(GamePanel.keyH);
    public static final TileManager TILE_MANAGER = new TileManager();
}
