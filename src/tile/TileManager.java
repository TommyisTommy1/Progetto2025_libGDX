package tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Gioco.GamePanel;
import utils.Defines;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] n = new int[Defines.MASSIMA_ALTEZZA_COL][Defines.MASSIMA_ALTEZZA_RIG];

    public TileManager(GamePanel g){
        this.gamePanel = g;
        tile = new Tile[10];
        for (int i = 0; i < 5; i++) {
            tile[i] = new Tile();
            getTileImage(tile[i], i);
        }
        
        for (int i = 0; i < Defines.MASSIMA_ALTEZZA_COL; i++) {
            for (int j = 0; j < Defines.MASSIMA_ALTEZZA_RIG; j++) {
                n[i][j]=(int)(Math.random() * 3);
            }
        }
    }

    public void getTileImage(Tile tile, int i){
        switch (i)
        {
            case 0: 
                try {
                    tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass1.png"));
                } catch (Exception e) {
                    System.err.println("tile non trovato");
                }
                break;
            case 1:
                try {
                    tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass2.png"));
                } catch (Exception e) {
                    System.err.println("tile non trovato");
                }
                break;
            case 2:
                try {
                    tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top_left.png"));
                } catch (Exception e) {
                    System.err.println("tile non trovato");
                }
                break;
            case 3:
                try {
                    tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top.png"));
                } catch (Exception e) {
                    System.err.println("tile non trovato");
                }
                break;
            case 4:
                try {
                    tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top_right.png"));
                } catch (Exception e) {
                    System.err.println("tile non trovato");
                }
                break;
            default:
                System.err.println("Tile non valido");
                break;
        }
    }
    public void draw(Graphics2D g){

        for (int i = 0; i < Defines.MASSIMA_ALTEZZA_COL; i++) {
            for (int j = 0; j < Defines.MASSIMA_ALTEZZA_RIG; j++) {
                if (n[i][j] == 0 || n[i][j] == 1) {
                    g.drawImage(tile[0].image, Defines.GRANDEZZA_CASELLE*i, Defines.GRANDEZZA_CASELLE*j, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);
                }else{
                    g.drawImage(tile[1].image, Defines.GRANDEZZA_CASELLE*i, Defines.GRANDEZZA_CASELLE*j, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);
                }
            }
        }
        
        
        g.drawImage(tile[2].image, Defines.GRANDEZZA_CASELLE*2, Defines.GRANDEZZA_CASELLE*2, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);
    }
}
