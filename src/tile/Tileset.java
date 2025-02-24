package tile;

import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import utils.Spritesheet;

public class Tileset {
    Spritesheet grass;
    Spritesheet path;
    Tile[] tile;
    int tileCount = 2;
    private int[] randomGrassTiles;
    private int[] randomPathTiles;

    public Tileset() {
        tile = new Tile[100];
        randomGrassTiles = new int[100]; // Array per memorizzare i tipi casuali di erba
        randomPathTiles = new int[100];  // Array per memorizzare i tipi casuali di sentiero
        Random rand = new Random();

        // Inizializza i tile e genera immagini casuali una sola volta
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
            randomGrassTiles[i] = rand.nextInt(10); // 10 tipi di erba
            randomPathTiles[i] = rand.nextInt(15);  // 15 tipi di sentiero
        }

        // Carica le immagini e imposta la collisione correttamente
        tile[tileCount].image = loadTileImage("stonebrick.png");
        tile[tileCount-1].collision = true;

        tile[tileCount].image = loadTileImage("mattons.png");
        tile[tileCount-1].collision = false;

        // Carica gli sprite per il sentiero e l'erba
        path = new Spritesheet(15, 0, "/res/tile/", "path.png");
        grass = new Spritesheet(10, 0, "/res/tile/", "grass.png");
    }

    // Metodo per caricare un'immagine tile
    private BufferedImage loadTileImage(String percorso) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso)));
            tileCount++;
            return img;
        } catch (Exception e) {
            System.err.println("Tile non trovato: " + percorso);
            return null;
        }
    }

    // Restituisce l'immagine di un tile in base all'indice
    public BufferedImage getTile(int i) {
        BufferedImage image;

        switch (i) {
            case 0: // Erba
                image = grass.getSpriteSheet(randomGrassTiles[i % randomGrassTiles.length]);
                break;
            case 1: // Sentiero
                image = path.getSpriteSheet(randomPathTiles[i % randomPathTiles.length]);
                break;
            default: // Altri tipi di tile
                if (i >= 0 && i < tile.length) {
                    image = tile[i].image;
                } else {
                    image = null; // Gestione degli indici fuori limite
                }
                break;
        }

        return image;
    }

    public boolean getCollision(int i) {
        boolean collision;

        switch (i) {
            case 0: // Erba
                collision=false;
                break;
            case 1: // Sentiero
                collision=false;
                break;
            default: // Altri tipi di tile
                System.out.println(tile[i].getCollision());
                collision=tile[i].getCollision();
                break;
        }
        System.out.println(collision);
        return collision;
    }
}