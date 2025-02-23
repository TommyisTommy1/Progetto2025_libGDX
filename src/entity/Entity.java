package entity;

import java.awt.Rectangle;
import utils.Defines;

public abstract class Entity {

    protected int speed; // velocità
    protected double delta; // moltiplicatore di velocità

    protected int spriteCounter = 0; 
    protected int spriteNum = 1; // frame attuale
    public Rectangle areaCollisione; // area di collisione
    public boolean inCollisione = false; // se è in collisione

    private String direction; // direzione

    public Entity() {
    }
    protected void setDirezione(String d) { 
        this.direction = d;
    }

    public String getDirezione() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    protected void setSpeed(int speed, double delta) {
        this.speed = (int) Math.ceil((double) speed * delta/(Defines.FPS/60));
        this.delta = delta;
    }
}
