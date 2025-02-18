package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import Gioco.GamePanel;
import entity.Entity;
import entity.Player;
import utils.Defines;

public class TileManager {
    public Tile[] tile;
    public int[][] n;
    public int misurax, misuray, uscitaNum;
    Casella[] uscita;
    int mappa = -1;
    boolean flag=false;

    public TileManager() {
        tile = new Tile[10];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        getTileImage();
    }

    private void loadMap(String mappa, String misure) {
        try {
            InputStream is = getClass().getResourceAsStream("/res/map/".concat(misure));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            String numbers1[] = line.split(" ");

            misurax = Integer.parseInt(numbers1[0]);
            misuray = Integer.parseInt(numbers1[1]);
            uscitaNum = Integer.parseInt(numbers1[2]);
            uscita = new Casella[uscitaNum];

            System.out.println(uscitaNum);

            for (int i = 0; i < uscitaNum; i++) {
                uscita[i] = new Casella();
                uscita[i].setCol(Integer.parseInt(numbers1[3 + (i * 2)]));
                uscita[i].setRow(Integer.parseInt(numbers1[4 + (i * 2)])); //ASSEGNO COORDINATE USCITA

                System.out.println(uscita[i].getCol());
                System.out.println(uscita[i].getRow());
            }
            Defines.GAME_PANEL.setMaxWorldCol(misurax);
            Defines.GAME_PANEL.setMaxWorldRow(misuray); //MISURE MASSIMO MONDO

            n = new int[misurax][misuray];
            
            is = getClass().getResourceAsStream("/res/map/".concat(mappa));
            br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < misuray) {

                line = br.readLine();
                String numbers2[] = line.split(" ");

                for (int col = 0; col < misurax; col++) {
                    n[col][row] = Integer.parseInt(numbers2[col]);
                }

                row++;
            }

        } catch (Exception e) {

        }
    }

    private void getTileImage() { // metodo per assegnare un immagine a un tile

        tile[0].image = loadTileImage("grass1.png");

        tile[1].image = loadTileImage("grass2.png");

        tile[2].image = loadTileImage("water_top_left.png");
        tile[2].collision = true;

        tile[3].image = loadTileImage("water_top.png");
        tile[3].collision = true;

        tile[4].image = loadTileImage("water_top_right.png");
        tile[4].collision = true;

        tile[5].image = loadTileImage("stonebrick.png");
        tile[5].collision = true;
    }

    private BufferedImage loadTileImage(String percorso) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso)));
        } catch (Exception e) {
            System.err.println("Tile non trovato in: " + percorso);
            return null;
        }
    }

    public void draw(Graphics2D g) {
        int worldCol = 0, worldRow = 0;
        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        int playerCol = (Player.getWorldX()/grandezzaCaselle);
        int playerRow = (Player.getWorldY()/grandezzaCaselle);
        
        boolean e =GamePanel.keyH.getPremuto("E");
            
        if (mappa==-1) {
            loadMap("map02.txt", "misureMap02.txt");
            mappa=2;
        }

        for(int i = 0; i < uscitaNum; i++){
            int col = uscita[i].getCol();
            int row = uscita[i].getRow();
            System.out.println("col player"+ playerCol);
            System.out.println("row player"+ playerRow);
            System.out.println(col);
            System.out.println(row);
            
            if (playerCol == col && playerRow == row && e) {
                flag=true;
                switch (mappa) {
                    case 1:
                        loadMap("map02.txt", "misureMap02.txt");
                        Defines.PLAYER.setWorldX(Defines.GRANDEZZA_CASELLE*7);
                        Defines.PLAYER.setWorldY(Defines.GRANDEZZA_CASELLE*12);
                        mappa=2;
    
                        break;
                    case 2:
                        loadMap("map01.txt", "misureMap01.txt");
                        Defines.PLAYER.setWorldX(Defines.GRANDEZZA_CASELLE*8);
                        Defines.PLAYER.setWorldY(Defines.GRANDEZZA_CASELLE*3);
                        mappa=1;
                        
                        break;
                    default:
                        break;
                }
                
            }
            if (playerCol != col && playerRow != row && !e) {
                flag=false;
                
                
            }
        }
        
        

        while (worldCol < GamePanel.getMaxWorldCol() && worldRow < GamePanel.getMaxWorldRow()) {

            int tileNum = n[worldCol][worldRow];

            int worldX = worldCol * grandezzaCaselle;
            int worldY = worldRow * grandezzaCaselle;
            int screenX = worldX - Entity.getWorldX() + Defines.PLAYER.getScreenX();
            int screenY = worldY - Entity.getWorldY() + Defines.PLAYER.getScreenY();

            if (worldX + grandezzaCaselle > Entity.getWorldX() - Defines.PLAYER.getScreenX()
                    && worldX - grandezzaCaselle < Entity.getWorldX() + Defines.PLAYER.getScreenX()
                    && worldY + grandezzaCaselle > Entity.getWorldY() - Defines.PLAYER.getScreenY()
                    && worldY - grandezzaCaselle < Entity.getWorldY() + Defines.PLAYER.getScreenY())
                    g.drawImage(tile[tileNum].image, screenX, screenY, grandezzaCaselle, grandezzaCaselle,
                        null);
                        
            worldCol++;
            if (worldCol == GamePanel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
