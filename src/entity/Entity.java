package entity;

import java.awt.Rectangle;
import utils.Defines;

public class Entity {
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

    

    public int getSpeed() {
        return this.speed;
    }

    protected void setSpeed(int speed, double delta) {
        this.speed = (int) Math.ceil((double) speed * delta/(Defines.FPS/60));
        this.delta = delta;
    }
}
