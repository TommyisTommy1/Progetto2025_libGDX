package utils;

import dialog.DialogMngr;
import entity.Player;
import game.GamePanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tile.TileManager;

public class Defines {
    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    // VARIABILI SCHERMO 
    public static final int SCREEN_WIDTH =1280;//(int)toolkit.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = 720;//(int)toolkit.getScreenSize().getHeight();
    public static final JFrame MAINFRAME = new JFrame("GameScreen");
    public static final ImageIcon ICONIMG = new ImageIcon("src/res/images/icon.png");

    // VARIABILI GAMEPANEL
    public static final int GRANDEZZA_CASELLE_ORIGINALE = 32;

    public static int GRANDEZZA_CASELLE = (int) Math .ceil(GRANDEZZA_CASELLE_ORIGINALE * GamePanel.SCALA); //L'ultimo numero è il moltiplicatore della telecamera
    
    //DIALOGHI
    public static final DialogMngr mngrDialog = new DialogMngr();
    // VARIABILI PANNELLI (DA SPIEGARE SPARTIX)
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final JPanel CONTENT_PANEL = new JPanel(new BorderLayout());

    public static final int FPS = 120; //validi solo multipli di 60, non sotto i 60

    public static final Thread THREAD_GIOCO = new Thread(GAME_PANEL);
    public static final Player PLAYER = new Player(GamePanel.keyH);
    public static final TileManager TILE_MANAGER = new TileManager();

    public static Camera CAMERA = new Camera();
}
