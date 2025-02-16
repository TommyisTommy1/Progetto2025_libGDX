package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Gioco.GamePanel;
import utils.Defines;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] n;

    public TileManager(GamePanel g){
        this.gamePanel = g;
        tile = new Tile[10];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        n = new int[Defines.MASSIMA_ALTEZZA_COL][Defines.MASSIMA_ALTEZZA_RIG];
        loadMap("/res/map/map01.txt");
        getTileImage();
    }
    public void loadMap(String s){
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < Defines.MASSIMA_ALTEZZA_RIG){
                String line = br.readLine();

                String numbers[] = line.split(" ");

                for (int col = 0; col < Defines.MASSIMA_ALTEZZA_COL; col++) {
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
        int posizioneX=0, posizioneY=0;
        int col=0, row=0;
        
        while (col < Defines.MASSIMA_ALTEZZA_COL && row < Defines.MASSIMA_ALTEZZA_RIG) {

            int tileNum = n[col][row];

            g.drawImage(tile[tileNum].image, posizioneX, posizioneY, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);    
            col++;  
            posizioneX+=Defines.GRANDEZZA_CASELLE;
            if (col == Defines.MASSIMA_ALTEZZA_COL) {
                    col=0;
                    posizioneX=0;
                    row++;  
                    posizioneY+=Defines.GRANDEZZA_CASELLE;
                }
        }
    }
}
