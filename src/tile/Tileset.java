package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utils.Spritesheet;

public class Tileset {
    Spritesheet grass;
    Spritesheet path;
    Tile[] tile;
    int tileCount = 0;

    public Tileset() {
        tile = new Tile[100];

        // Inizializza i tile e genera immagini casuali
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }

        // Carica le immagini e collisione
        tile[tileCount].image = loadTileImage("stonebrick.png");
        tile[tileCount-1].collision = true;

        tile[tileCount].image = loadTileImage("mattons.png");
        tile[tileCount-1].collision = false;

        // Carica gli sprite per path e grass
        path = new Spritesheet(15, 0, "/res/tile/", "path.png");

        grass = new Spritesheet(10, 0, "/res/tile/", "grass.png");

        for (int i = 0; i < grass.getNum(); i++) {
            tile[tileCount].image = grass.getSpriteSheet(i);
            tile[tileCount].collision = false;
            tileCount++;
        }

        for (int i = 0; i < path.getNum(); i++) {
            tile[tileCount].image = path.getSpriteSheet(i);
            tile[tileCount].collision = false;
            tileCount++;
        }
        
        
    }

    // Metodo per caricare un'immagine tile
    private BufferedImage loadTileImage(String percorso) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso)));
            tileCount++;
            return img;
        } catch (IOException e) {
            System.err.println("Tile non trovato: " + percorso);
            return null;
        }
    }

    // Restituisce l'immagine di un tile in base all'indice
    public BufferedImage getTile(int i) {
        BufferedImage image;

        image = tile[i].image;

        return image;
    }

    public boolean getCollision(int i) {
        boolean collision;

        collision = tile[i].collision;
        
        return collision;
    }
}