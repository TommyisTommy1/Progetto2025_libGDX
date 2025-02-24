package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {
    private BufferedImage[] image;
    private int num;

    public Spritesheet(int num, int row, String path, String file) {
        this.num = num;
        this.image = new BufferedImage[num];

        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(path.concat(file))); //carica l'immagine 
            for (int index = 0; index < num; index++) {
                int x = index * Defines.GRANDEZZA_CASELLE_ORIGINALE;
                int y = row * Defines.GRANDEZZA_CASELLE_ORIGINALE;
                image[index] = spriteSheet.getSubimage(x, y, 32, 32); //divide in immagini piu piccole
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Immagine non trovata in: " + file);
        }
    }

    public BufferedImage getSpriteSheet(int i) {
        if (i >= 0 && i < num) {
            return image[i];
        } else {
            System.err.println("Indice fuori dal range: " + i);
            return null;
        }
    }

    public void flipImmagine() { //flippa l'immagine
        for (int index = 0; index < num; index++) {
            int width = image[index].getWidth();
            int height = image[index].getHeight();
            BufferedImage immagineFlippata = new BufferedImage(width, height, image[index].getType());
            Graphics2D g = immagineFlippata.createGraphics();
            g.drawImage(image[index], width, 0, -width, height, null);
            g.dispose();
            image[index] = immagineFlippata;
        }
    }

    public void overlayImmagine(BufferedImage overlay) { //sovrappone l'immagine
        for (int index = 0; index < num; index++) {
            int width = image[index].getWidth();
            int height = image[index].getHeight();
            BufferedImage immagineSovrapposta = new BufferedImage(width, height, image[index].getType());
            Graphics2D g = immagineSovrapposta.createGraphics();
            g.drawImage(image[index], 0, 0, null);
            g.drawImage(overlay, 0, 0, null);
            g.dispose();
            image[index] = immagineSovrapposta;
        }
    }
    public void overlayImmagine(Spritesheet overlay) { //sovrappone l'immagine
        for (int index = 0; index < num; index++) {
            int width = image[index].getWidth();
            int height = image[index].getHeight();
            BufferedImage immagineSovrapposta = new BufferedImage(width, height, image[index].getType());
            Graphics2D g = immagineSovrapposta.createGraphics();
            g.drawImage(image[index], 0, 0, null);
            g.drawImage(overlay.getSpriteSheet(index), 0, 0, null);
            g.dispose();
            image[index] = immagineSovrapposta;
        }
    }

    public int getNum(){
        return this.num;
    }
}
