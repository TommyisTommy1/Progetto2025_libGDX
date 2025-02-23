package entity;

import java.awt.Rectangle;
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

    protected void setDirezione(String d) {
        this.direction = d;
    }

    public String getDirezione() {
        return this.direction;
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
        this.speed = (int) Math.ceil((double) speed * delta/(Defines.FPS/60));
        this.delta = delta;
    }
}
