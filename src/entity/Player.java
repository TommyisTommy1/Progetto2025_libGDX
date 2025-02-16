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

    private void setDefaultValues() {
        worldX = Defines.GRANDEZZA_CASELLE * 8;
        worldY = Defines.GRANDEZZA_CASELLE * 5;
        speed = 4;
        setDirezione("su");
        getPlayerImage();
    }
    
    private BufferedImage loadPlayerImage(String percorso) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/player/".concat(percorso)));
        } catch (Exception e) {
            System.err.println("Immagine non trovata in: " + percorso);
            return null;
        }
    }

    public int getScreenX(){
        return this.screenX;
    }
    public int getScreenY(){
        return this.screenY;
    }

    private boolean getPremuto(String key){
        boolean stato=false;
        stato = gestioneTasti.getPremuto(key);
        return stato;
    }

    private void spriteCounter(){
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
                        
    private void spostaX(String direction, String operazione) {
        spriteCounter();
        setDirezione(direction);
        if (operazione.equals("aggiungi")) worldX+=speed;
        if (operazione.equals("sottrai")) worldX-=speed;
    }
    private void spostaY(String direction, String operazione) {
        spriteCounter();
        setDirezione(direction);
        if (operazione.equals("aggiungi")) worldY+=speed;
        if (operazione.equals("sottrai")) worldY-=speed;
    }
                    
    private void getPlayerImage() {
        fermoSu = loadPlayerImage("playerFermoSu.png");
        fermoGiu = loadPlayerImage("playerFermoGiu.png");
        su1 = loadPlayerImage("player_up_1.png");
        su2 = loadPlayerImage("player_up_2.png");
        giu1 = loadPlayerImage("player_down_1.png");
        giu2 = loadPlayerImage("player_down_2.png");
        destra1 = loadPlayerImage("player_right_1.png");
        destra2 = loadPlayerImage("player_right_2.png");
        sinistra1 = loadPlayerImage("player_left_1.png");
        sinistra2 = loadPlayerImage("player_left_2.png");
    }


    public void update() {
        boolean w = getPremuto("W");
        boolean s = getPremuto("S");
        boolean d = getPremuto("D");
        boolean a = getPremuto("A");

        if (w || s || d || a) {
            if (a && d) {

                setDirezione("fermoGiu");

                if (w) spostaY("su", "sottrai");
                if (s) spostaY("giu", "aggiungi");

            }else if (w && s) {
                setDirezione("fermoGiu");
                if (d) spostaX("destra", "aggiungi");
                if (a) spostaX("sinistra", "sottrai");

        }else{

            if (w) spostaY("su", "sottrai");
            if (s) spostaY("giu", "aggiungi");
            if (d) spostaX("destra", "aggiungi");
            if (a)spostaX("sinistra", "sottrai");

        }
        } else {
            switch (getDirezione()) {
                case "giu":
                    spostaY("fermoGiu", "");
                    break;
                case "su":
                    spostaY("fermoSu", "");
                    break;
                case "destra":
                    spostaX("fermoGiu", "");
                    break;
                case "sinistra":
                    spostaX("fermoGiu", "");
                    break;
                default:
                    break;
            }
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
