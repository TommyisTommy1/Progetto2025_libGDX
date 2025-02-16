package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Gioco.GamePanel;
import Gioco.GestioneTasti;
import utils.Defines;

public class Player extends Entity{
        GamePanel panGioco;
        GestioneTasti gestioneTasti;

        public final int screenX;
        public final int screenY;


    public Player(GamePanel g1, GestioneTasti g2){
        this.panGioco = g1;
        gestioneTasti = g2;

        screenX = Defines.SCREEN_WIDTH/2 - Defines.GRANDEZZA_CASELLE;
        screenY = Defines.SCREEN_HEIGHT/2 - Defines.GRANDEZZA_CASELLE;

        setDefaultValues();
    }
    public void setDefaultValues(){
        worldX = Defines.GRANDEZZA_CASELLE*8;
        worldY = Defines.GRANDEZZA_CASELLE*5; 
        speed = 4;
        direction = "su";
        getPlayerImage();
    }
    public void update(){
        if (gestioneTasti.suPremuto || gestioneTasti.giuPremuto || gestioneTasti.destraPremuto || gestioneTasti.sinistraPremuto) {
            if (gestioneTasti.suPremuto) {
                direction = "su";
                worldY -= speed;
            }
            if (gestioneTasti.giuPremuto) {
                direction = "giu";
                worldY += speed;
            }
            if (gestioneTasti.destraPremuto) {
                direction = "destra";
                worldX += speed;
            }
            if (gestioneTasti.sinistraPremuto) {
                direction = "sinistra";
                worldX -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum=2;
                }else if(spriteNum == 2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }else{
            switch (direction) {
                case "giu":
            
                    direction = "fermoGiu";
                    spriteCounter++;
                    if (spriteCounter > 24) {
                        if (spriteNum == 1) spriteNum=2;
                        else if(spriteNum == 2) spriteNum=1;
                        spriteCounter=0;
                    }

                    break;
                case "su":
                    direction = "fermoSu";
                    spriteCounter++;
                    if (spriteCounter > 24) {
                        if (spriteNum == 1) spriteNum=2;
                        else if(spriteNum == 2) spriteNum=1;
                        spriteCounter=0;
                    }
                    break;
                case "destra":
                    direction = "fermoGiu";
                    spriteCounter++;
                    if (spriteCounter > 24) {
                        if (spriteNum == 1) spriteNum=2;
                        else if(spriteNum == 2) spriteNum=1;
                        spriteCounter=0;
                    }
                    break;
                case "sinistra":
                    direction = "fermoGiu";
                    spriteCounter++;
                    if (spriteCounter > 24) {
                        if (spriteNum == 1) spriteNum=2;
                        else if(spriteNum == 2) spriteNum=1;
                        spriteCounter=0;
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public void getPlayerImage(){
        try {
            fermoSu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/playerFermoSu.png"));
        } catch (Exception e) {
            System.err.println("erorre caricamento fermoSu");
        }


        try{
            fermoGiu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/playerFermoGiu.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento fermoGiu");
        }


        try{
            su1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_up_1.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento su1");
        }


        try{
            su2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_up_2.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento su2");
        }


        try{
            giu1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_down_1.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento giu1");
        }


        try{
            giu2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_down_2.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento giu2");
        }


        try{
            destra1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_right_1.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento destra1");
        }


        try{
            destra2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_right_2.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento destra2");
        }


        try{
            sinistra1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_left_1.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento sinistra1");
        }

        
        try{
            sinistra2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_left_2.png"));
        } catch (Exception e) {
            System.err.println("Errore caricamento sinistra2");
        }
    }

    public void draw(Graphics2D g){
        BufferedImage image = null;
        switch (direction) {
            case "su":
                
                if (spriteNum==1) image = su1;
                else image = su2;

                break;
            case "giu":

                if (spriteNum==1) image = giu1;
                else image = giu2;

                break;
            case "destra":

                if (spriteNum==1) image = destra1;
                else image = destra2;

                break;
            case "sinistra":

                if (spriteNum==1) image = sinistra1;
                else image = sinistra2;  

                break;
            case "fermoSu":
                image = fermoSu;
                break;
            case "fermoGiu":
                image = fermoGiu;
                break;
        }
        g.drawImage(image, worldX, worldY, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE, null);
    }
}
