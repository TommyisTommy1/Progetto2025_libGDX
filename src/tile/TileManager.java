package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import utils.Defines;

public class TileManager {
    public Tile[] tile;
    public int[][] n;

    public TileManager() {
        tile = new Tile[10];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        Defines.GAME_PANEL.setMaxWorldCol(50);
        Defines.GAME_PANEL.setMaxWorldRow(50);
        n = new int[Defines.GAME_PANEL.getMaxWorldCol()][Defines.GAME_PANEL.getMaxWorldRow()];
        loadMap("/res/map/map01.txt");
        getTileImage();
    }

    private void loadMap(String s) {
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < Defines.GAME_PANEL.getMaxWorldRow()) {
                String line = br.readLine();

                String numbers[] = line.split(" ");

                for (int col = 0; col < Defines.GAME_PANEL.getMaxWorldCol(); col++) {
                    n[col][row] = Integer.parseInt(numbers[col]);
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

        while (worldCol < Defines.GAME_PANEL.getMaxWorldCol() && worldRow < Defines.GAME_PANEL.getMaxWorldRow()) {

            int tileNum = n[worldCol][worldRow];

            int worldX = worldCol * grandezzaCaselle;
            int worldY = worldRow * grandezzaCaselle;
            int screenX = worldX - Defines.PLAYER.getWorldX() + Defines.PLAYER.getScreenX();
            int screenY = worldY - Defines.PLAYER.getWorldY() + Defines.PLAYER.getScreenY();

            if (worldX + grandezzaCaselle > Defines.PLAYER.getWorldX() - Defines.PLAYER.getScreenX()
                    && worldX - grandezzaCaselle < Defines.PLAYER.getWorldX() + Defines.PLAYER.getScreenX()
                    && worldY + grandezzaCaselle > Defines.PLAYER.getWorldY() - Defines.PLAYER.getScreenY()
                    && worldY - grandezzaCaselle < Defines.PLAYER.getWorldY() + Defines.PLAYER.getScreenY())
                g.drawImage(tile[tileNum].image, screenX, screenY, grandezzaCaselle, grandezzaCaselle,
                        null);

            worldCol++;
            if (worldCol == Defines.NUM_COLONNE) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
