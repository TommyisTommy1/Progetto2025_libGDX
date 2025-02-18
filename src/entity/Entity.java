package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.Defines;

public class Entity {
    protected static int worldX;
    protected static int worldY;
    protected int speed;
    protected double delta;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public Rectangle areaCollisione;
    public boolean inCollisione = false;

    private String direction;

    protected BufferedImage su[] = new BufferedImage[4];
    protected BufferedImage giu[] = new BufferedImage[4];
    protected BufferedImage destra[] = new BufferedImage[4];
    protected BufferedImage sinistra[] = new BufferedImage[4];
    protected BufferedImage fermo[] = new BufferedImage[8];

    protected void setDirezione(String d) {
        this.direction = d;
    }

    public String getDirezione() {
        return this.direction;
    }

    public static int getWorldX() {
        return worldX;
    }

    public static int getWorldY() {
        return worldY;
    }

    public void setWorldX(int n) {
        worldX = n;
    }

    protected int getCol(){
        return worldX/Defines.GRANDEZZA_CASELLE;
    }
    protected int getRow(){
        return worldY/Defines.GRANDEZZA_CASELLE;
    }

    public void setWorldY(int n) {
        worldY = n;
    }

    public int getSpeed() {
        return this.speed;
    }

    protected void setSpeed(int speed, double delta) {
        this.speed = (int) Math.ceil((double) speed * delta);
        this.delta = delta;
    }
}
