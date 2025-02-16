package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import utils.Defines;

public class TileManager {
    private Tile[] tile;
    private int[][] n;

    public TileManager(){
        tile = new Tile[10];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        n = new int[Defines.MASSIMA_ALTEZZA_MONDO_COL][Defines.MASSIMA_ALTEZZA_MONDO_RIG];
        loadMap("/res/map/map01.txt");
        getTileImage();
    }
    public void loadMap(String s){
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < Defines.MASSIMA_ALTEZZA_MONDO_RIG){
                String line = br.readLine();

                String numbers[] = line.split(" ");

                for (int col = 0; col < Defines.MASSIMA_ALTEZZA_MONDO_COL; col++) {
                    n[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }

        } catch (Exception e) {
           
        }
    }
    public void getTileImage(){ //metodo per assegnare un immagine a un tile 
                
                tile[0].image = loadImage("/res/tile/grass1.png");
                
                tile[1].image = loadImage("/res/tile/grass2.png");
                
                tile[2].image = loadImage("/res/tile/water_top_left.png");
                
                tile[3].image = loadImage("/res/tile/water_top.png");
                
                tile[4].image = loadImage("/res/tile/water_top_right.png");
        }
    private BufferedImage loadImage(String percorso){
        try {
            return ImageIO.read(getClass().getResourceAsStream(percorso));
        } catch (Exception e) {
            System.err.println("Tile non trovato in: "+percorso);
            return null;
        }
    }
    public void draw(Graphics2D g){
        int worldCol=0, worldRow=0;
        
        while (worldCol < Defines.MASSIMA_ALTEZZA_MONDO_COL && worldRow < Defines.MASSIMA_ALTEZZA_MONDO_RIG) {

            int tileNum = n[worldCol][worldRow];

            int worldX = worldCol * Defines.GRANDEZZA_CASELLE;
            int worldY = worldRow * Defines.GRANDEZZA_CASELLE;
            int screenX = worldX - Defines.PLAYER.worldX + Defines.PLAYER.getScreenX();
            int screenY = worldY - Defines.PLAYER.worldY + Defines.PLAYER.getScreenY();
            g.drawImage(tile[tileNum].image, screenX, screenY, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);    
            worldCol++;  
            if (worldCol == Defines.MASSIMA_ALTEZZA_COL) {
                    worldCol=0;
                    worldRow++;  
                }
        }
    }
}
