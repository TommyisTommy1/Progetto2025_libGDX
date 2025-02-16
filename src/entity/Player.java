package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Gioco.GestioneTasti;
import utils.Defines;

public class Player extends Entity {
    private GestioneTasti gestioneTasti;

    private final int screenX;
    private final int screenY;

    public Player(GestioneTasti g2) {
        gestioneTasti = g2;

        screenX = Defines.SCREEN_WIDTH / 2 - Defines.GRANDEZZA_CASELLE/2;
        screenY = Defines.SCREEN_HEIGHT / 2 - Defines.GRANDEZZA_CASELLE/2;

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = Defines.GRANDEZZA_CASELLE * 8;
        worldY = Defines.GRANDEZZA_CASELLE * 5;
        speed = 4;
        setDirezione("su");
        getPlayerImage();
    }

    public int getScreenX(){
        return this.screenX;
    }
    public int getScreenY(){
        return this.screenY;
    }


    public boolean getPremuto(String key){
        boolean stato=false;
        stato = gestioneTasti.getPremuto(key);
        return stato;
    }

    public void spriteCounter(){
        this.spriteCounter++;
        if (this.spriteCounter > 12) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }

    public void update() {
        boolean w = getPremuto("W");
        boolean s = getPremuto("S");
        boolean d = getPremuto("D");
        boolean a = getPremuto("A");

        if (w || s || d || a) {
            spriteCounter();
            if (w) {
                setDirezione("su");
                worldY -= speed;
            }
            if (s) {
                setDirezione("giu");
                worldY += speed;
            }
            if (d) {
                setDirezione("destra");
                worldX += speed;
            }
            if (a) {
                setDirezione("sinistra");
                worldX -= speed;
            }
            
        } else {
            spriteCounter();
            switch (getDirezione()) {
                case "giu":

                    setDirezione("fermoGiu");

                    break;
                case "su":

                    setDirezione("fermoSu");
                    
                    break;
                case "destra":

                    setDirezione("fermoGiu");
                    
                    break;
                case "sinistra":

                    setDirezione("fermoGiu");
                    
                    break;
                default:
                    break;
            }
        }
    }

    public void getPlayerImage() {
        fermoSu = loadImage("/res/player/playerFermoSu.png");

        fermoGiu = loadImage("/res/player/playerFermoGiu.png");

        su1 = loadImage("/res/player/player_up_1.png");

        su2 = loadImage("/res/player/player_up_2.png");

        giu1 = loadImage("/res/player/player_down_1.png");

        giu2 = loadImage("/res/player/player_down_2.png");

        destra1 = loadImage("/res/player/player_right_1.png");

        destra2 = loadImage("/res/player/player_right_2.png");

        sinistra1 = loadImage("/res/player/player_left_1.png");

        sinistra2 = loadImage("/res/player/player_left_2.png");
    }

    private BufferedImage loadImage(String percorso) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(percorso));
        } catch (Exception e) {
            System.err.println("Immagine non trovata in: " + percorso);
            return null;
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        switch (getDirezione()) {
            case "su":

                if (spriteNum == 1)image = su1;
                else image = su2;

                break;
            case "giu":

                if (spriteNum == 1) image = giu1;
                else image = giu2;

                break;
            case "destra":

                if (spriteNum == 1) image = destra1;
                else image = destra2;

                break;
            case "sinistra":

                if (spriteNum == 1) image = sinistra1;
                else image = sinistra2;

                break;
            case "fermoSu":
                image = fermoSu;
                break;
            case "fermoGiu":
                image = fermoGiu;
                break;
        }
        g.drawImage(image, screenX, screenY, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);
    }
}
