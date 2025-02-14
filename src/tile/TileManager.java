package tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Gioco.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] n = new int[18][12];

    public TileManager(GamePanel g){
        this.gamePanel = g;
        tile = new Tile[10];
        getTileImage();

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 12; j++) {
                n[i][j]=(int)(Math.random() * 3);
            }
        }
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass1.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top_left.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_top_right.png"));

        } catch (Exception e) {
            System.err.println("tile non trovato");
        }
    }
    public void draw(Graphics2D g){

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 12; j++) {
                if (n[i][j] == 0 || n[i][j] == 1) {
                    g.drawImage(tile[0].image, gamePanel.grandezzaCaselle*i, gamePanel.grandezzaCaselle*j, gamePanel.grandezzaCaselle, gamePanel.grandezzaCaselle, null);
                }else{
                    g.drawImage(tile[1].image, gamePanel.grandezzaCaselle*i, gamePanel.grandezzaCaselle*j, gamePanel.grandezzaCaselle, gamePanel.grandezzaCaselle, null);
                }
            }
        }
        
        
        g.drawImage(tile[2].image, gamePanel.grandezzaCaselle*2, gamePanel.grandezzaCaselle*2, gamePanel.grandezzaCaselle, gamePanel.grandezzaCaselle, null);
    }
}
