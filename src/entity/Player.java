package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Gioco.GamePanel;
import Gioco.GestioneTasti;

public class Player extends Entity{
        GamePanel panGioco;
        GestioneTasti gestioneTasti;

    public Player(GamePanel g1, GestioneTasti g2){
        this.panGioco = g1;
        this.gestioneTasti = g2;

        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100; 
        speed = 4;
        direction = "su";
        getPlayerImage();
    }
    public void update(){

        if (gestioneTasti.suPremuto || gestioneTasti.giuPremuto || gestioneTasti.destraPremuto || gestioneTasti.sinistraPremuto) {
            if (gestioneTasti.suPremuto == true) {
                direction = "su";
                y -= speed;
            }
            if (gestioneTasti.giuPremuto == true) {
                direction = "giu";
                y += speed;
            }
            if (gestioneTasti.destraPremuto == true) {
                direction = "destra";
                x += speed;
            }
            if (gestioneTasti.sinistraPremuto == true) {
                direction = "sinistra";
                x -= speed;
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
            if (direction.equals("su")) {
                direction = "fermoSu";
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum=2;
                    }else if(spriteNum == 2){
                        spriteNum=1;
                    }
                    spriteCounter=0;
                }
            }
            if (direction.equals("giu")) {
                direction = "fermoGiu";
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum=2;
                    }else if(spriteNum == 2){
                        spriteNum=1;
                    }
                    spriteCounter=0;
                }
            }
            if (direction.equals("giu")) {
                direction = "fermoGiu";
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum=2;
                    }else if(spriteNum == 2){
                        spriteNum=1;
                    }
                    spriteCounter=0;
                }
            }
            if (direction.equals("destra")) {
                direction = "fermoGiu";
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum=2;
                    }else if(spriteNum == 2){
                        spriteNum=1;
                    }
                    spriteCounter=0;
                }
            }
            if (direction.equals("sinistra")) {
                direction = "fermoGiu";
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum=2;
                    }else if(spriteNum == 2){
                        spriteNum=1;
                    }
                    spriteCounter=0;
                }
            }
        }
        
    }
    public void getPlayerImage(){
        try {
            fermoSu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/playerFermoSu.png"));
            fermoGiu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/playerFermoGiu.png"));
            su1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_up_1.png"));
            su2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_up_2.png"));
            giu1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_down_1.png"));
            giu2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_down_2.png"));
            destra1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_right_1.png"));
            destra2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_right_2.png"));
            sinistra1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_left_1.png"));
            sinistra2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/player_left_2.png"));
        } catch (Exception e) {
            System.err.println("ciao");
        }
    }
    public void draw(Graphics2D g){
        BufferedImage image = null;
        switch (direction) {
            case "su":
                if (spriteNum==1) {
                    image = su1;
                }
                if (spriteNum==2) {
                    image = su2;
                }
                break;
            case "giu":
            if (spriteNum==1) {
                image = giu1;
            }
            if (spriteNum==2) {
                image = giu2;
            }
                break;
            case "destra":
            if (spriteNum==1) {
                image = destra1;
            }
            if (spriteNum==2) {
                image = destra2;
            }
                break;
            case "sinistra":
            if (spriteNum==1) {
                image = sinistra1;
            }
            if (spriteNum==2) {
                image = sinistra2;
            }    
                break;
            case "fermoSu":
                image = fermoSu;
                break;
            case "fermoGiu":
                image = fermoGiu;
                break;
        }
        g.drawImage(image, x, y, panGioco.grandezzaCaselle, panGioco.grandezzaCaselle, null);
    }
}
