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
    public int misurax, misuray;
    Casella[] uscita, spawn;
    int mappa = -1;
    boolean flag = false;

    public TileManager() {
        tile = new Tile[100];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        getTileImage();
    }

    private void loadMap(String mappa, String misure, String uscite, String entrate) {
        try {
            misurax = misuray = 0;
            uscita = loadCaselle(uscite);
            spawn = loadCaselle(entrate);
            
            InputStream is1 = getClass().getResourceAsStream("/res/map/".concat(misure));
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
            String[] numbers = br1.readLine().split(" ");
            misurax = Integer.parseInt(numbers[0]);
            misuray = Integer.parseInt(numbers[1]);
            
            Defines.GAME_PANEL.setMaxWorldCol(misurax);
            Defines.GAME_PANEL.setMaxWorldRow(misuray);
            
            n = new int[misurax][misuray];
            
            is1 = getClass().getResourceAsStream("/res/map/".concat(mappa));
            br1 = new BufferedReader(new InputStreamReader(is1));
            
            for (int row = 0; row < misuray; row++) {
                numbers = br1.readLine().split(" ");
                for (int col = 0; col < misurax; col++) {
                    n[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Casella[] loadCaselle(String filePath) throws Exception {
        InputStream is = getClass().getResourceAsStream("/res/map/".concat(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String[] numbers = br.readLine().split(" ");
        
        Casella[] caselle = new Casella[numbers.length / 2];
        for (int i = 0; i < numbers.length; i += 2) {
            caselle[i / 2] = new Casella(Integer.parseInt(numbers[i]), Integer.parseInt(numbers[i + 1]));
        }
        return caselle;
    }

    private void getTileImage() {
        tile[0].image = loadTileImage("grass1.png");
        tile[1].image = loadTileImage("grass2.png");
        tile[2].image = loadTileImage("water_top_left.png"); tile[2].collision = true;
        tile[3].image = loadTileImage("water_top.png"); tile[3].collision = true;
        tile[4].image = loadTileImage("water_top_right.png"); tile[4].collision = true;
        tile[5].image = loadTileImage("stonebrick.png"); tile[5].collision = true;
        tile[6].image = loadTileImage("top_left.png"); tile[6].collision = true;
        tile[7].image = loadTileImage("top_right.png"); tile[7].collision = true;
        tile[8].image = loadTileImage("bottom_left.png"); tile[8].collision = true;
        tile[9].image = loadTileImage("bottom_right.png"); tile[9].collision = true;
        tile[10].image = loadTileImage("acqua.png"); tile[10].collision = true;
        tile[11].image = loadTileImage("mattons.png"); tile[11].collision = true;
    }

    private BufferedImage loadTileImage(String percorso) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso)));
        } catch (Exception e) {
            System.err.println("Tile non trovato: " + percorso);
            return null;
        }
    }

    public void draw(Graphics2D g) {
        if (mappa == -1) {
            loadMap("map02.txt", "misureMap02.txt", "uscita02.txt", "spawn02.txt");
            mappa = 2;
        }
        
        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        int playerCol = Player.getWorldX() / grandezzaCaselle;
        int playerRow = Player.getWorldY() / grandezzaCaselle;
        boolean e = GamePanel.keyH.getPremuto("E");

        for (int i = 0; i < uscita.length; i++) {
            if (playerCol == uscita[i].getCol() && playerRow == uscita[i].getRow() && e && !flag) {
                cambiaMappa(i);  // Passiamo l'indice dell'uscita usata
                flag=true;
                break;
            }
            if (!e && flag==true) {
                flag=false;
            }
        }
        

        for (int row = 0; row < misuray; row++) {
            for (int col = 0; col < misurax; col++) {
                int tileNum = n[col][row];
                int worldX = col * grandezzaCaselle;
                int worldY = row * grandezzaCaselle;
                int screenX = worldX - Entity.getWorldX() + Defines.PLAYER.getScreenX();
                int screenY = worldY - Entity.getWorldY() + Defines.PLAYER.getScreenY();
                
                if (isVisible(worldX, worldY, grandezzaCaselle)) {
                    g.drawImage(tile[tileNum].image, screenX, screenY, grandezzaCaselle, grandezzaCaselle, null);
                }
            }
        }
    }

    private void cambiaMappa(int uscitaIndex) {
        switch (mappa) {
            case 1:
                loadMap("map02.txt", "misureMap02.txt", "uscita02.txt", "spawn02.txt");
                mappa = 2;
                break;
            case 2:
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt");
                mappa = 1;
                break;
        }
        
        if (uscitaIndex >= 0 && uscitaIndex < spawn.length) {
            Defines.PLAYER.setWorldX(Defines.GRANDEZZA_CASELLE * spawn[uscitaIndex].getCol());
            Defines.PLAYER.setWorldY(Defines.GRANDEZZA_CASELLE * spawn[uscitaIndex].getRow());
        }
    }
    

    private boolean isVisible(int worldX, int worldY, int grandezzaCaselle) {
        return worldX + grandezzaCaselle > Entity.getWorldX() - Defines.PLAYER.getScreenX() &&
               worldX - grandezzaCaselle < Entity.getWorldX() + Defines.PLAYER.getScreenX() &&
               worldY + grandezzaCaselle > Entity.getWorldY() - Defines.PLAYER.getScreenY() &&
               worldY - grandezzaCaselle < Entity.getWorldY() + Defines.PLAYER.getScreenY();
    }
}
