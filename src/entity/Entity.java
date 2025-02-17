package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    protected int worldX;
    protected int worldY;
    protected int speed;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public Rectangle areaCollisione;
    public boolean inCollisione = false;

    private String direction;
    
    protected BufferedImage su[] = new BufferedImage[4];
    protected BufferedImage giu[] = new BufferedImage[4];
    protected BufferedImage destra[] = new BufferedImage[4];
    protected BufferedImage sinistra[] = new BufferedImage[4];
    protected BufferedImage fermo[] = new BufferedImage[4];

    protected void setDirezione(String d) {
        this.direction = d;
    }

    public String getDirezione() {
        return this.direction;
    }

    public int getWorldX() {
        return this.worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public int getSpeed() {
        return this.speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }
}
