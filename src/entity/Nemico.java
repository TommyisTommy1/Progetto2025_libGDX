package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import utils.Camera;
import utils.Defines;
import utils.Spritesheet;
import utils.SpritesheetEntity;

public class Nemico extends Entity {
    boolean isAlive;
    boolean isAliveAnimation = false;

    int worldX;
    int worldY;

    SpritesheetEntity moving;
    SpritesheetEntity notMoving;
    Spritesheet dying;

    public Nemico() {
        setDefaultValues();
    }

    protected int getCol(){
        return worldX/Defines.GRANDEZZA_CASELLE;
    }
    protected int getRow(){
        return worldY/Defines.GRANDEZZA_CASELLE;
    }

    private void setDefaultValues() {
        worldX = 3 * Defines.GRANDEZZA_CASELLE;
        worldY = 3 * Defines.GRANDEZZA_CASELLE;
        setSpeed(1, 1);
        isAlive = true;
        setDirezione("su");
        getNemicoImage();
    }
        
    private void getNemicoImage() {
        
    }
        
    private void spostaX(String direction, String operazione) {
        setDirezione(direction);
        if (operazione.equals("aggiungi"))
            worldX += speed;
        if (operazione.equals("sottrai"))
            worldX -= speed;
    }

    private void spostaY(String direction, String operazione) {
        setDirezione(direction);
        if (operazione.equals("aggiungi"))
            worldY += speed;
        if (operazione.equals("sottrai"))
            worldY -= speed;
    }
    
    public void draw(Graphics2D g) {
        Defines.CAMERA.update();
        Defines.CAMERA.setCameraCasella(getCol(), getRow());
        if (isVisible(Defines.CAMERA)) {
            g.setColor(Color.RED);
            g.fillRect(Defines.CAMERA.getScreenX(), Defines.CAMERA.getScreenY(), Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE);
        }
        
    }

    private boolean isVisible(Camera camera) { //controlla se il tile è visibile
        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        return camera.getCameraWorldX() + grandezzaCaselle > worldX - Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldX() - grandezzaCaselle < worldX + Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldY() + grandezzaCaselle > worldY - Defines.PLAYER.getScreenY() &&
            camera.getCameraWorldY() - grandezzaCaselle < worldY + Defines.PLAYER.getScreenY();
    }

        
}
