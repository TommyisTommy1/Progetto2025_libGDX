package entity;

import game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import tile.TileManager;
import utils.Camera;
import utils.Defines;
import utils.Drawable;
import utils.Spritesheet;
import utils.SpritesheetEntity;

public class Nemico extends Entity implements Drawable{
    boolean isAlive;
    boolean isAliveAnimation = false;

    int worldX;
    int worldY;
    int screenX;
    int screenY;

    SpritesheetEntity moving;
    SpritesheetEntity notMoving;
    Spritesheet dying;

    public Nemico() {
        setDefaultValues();
    }

    @Override
    public void draw(Graphics2D g) {
        Defines.CAMERA.update();
        Defines.CAMERA.setCameraCasella(getCol(), getRow());
        drawNemico(g);
    }

    public void translate(int x, int y, Graphics2D g){
        g.translate(x, y);
    }

    public void untranslate(int x, int y, Graphics2D g){
        g.translate(-x, -y);
    }

    public void translateTemp(int x, int y, Graphics2D g){
        int temp = Defines.SCREEN_WIDTH/2 - GamePanel.getMaxWorldCol()*Defines.GRANDEZZA_CASELLE/2;
        g.translate(x+temp, y);
    }
    
    public void untranslateTemp(int x, int y, Graphics2D g){
        int temp = Defines.SCREEN_WIDTH/2 - GamePanel.getMaxWorldCol()*Defines.GRANDEZZA_CASELLE/2;
        g.translate(-x-temp, -y);
    }

    private void setDefaultValues() {
        worldX = 3 * Defines.GRANDEZZA_CASELLE;
        worldY = 3 * Defines.GRANDEZZA_CASELLE;
        setSpeed(1, 1);
        isAlive = true;
        setDirezione("su");
        getNemicoImage();
    }

    protected int getCol(){
        return worldX/Defines.GRANDEZZA_CASELLE;
    }

    protected int getRow(){
        return worldY/Defines.GRANDEZZA_CASELLE;
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

    private boolean isVisible(Camera camera) { //controlla se il tile è visibile
        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        return camera.getCameraWorldX() + grandezzaCaselle > worldX - Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldX() - grandezzaCaselle < worldX + Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldY() + grandezzaCaselle > worldY - Defines.PLAYER.getScreenY() &&
            camera.getCameraWorldY() - grandezzaCaselle < worldY + Defines.PLAYER.getScreenY();
    }

    public void drawNemico(Graphics2D g){
        if (TileManager.ambienteAperto) {
            screenX=worldX;
            screenY=worldY;
        }else{
            screenX=Defines.CAMERA.getScreenX();
            screenY=Defines.CAMERA.getScreenY();
        }
        if (TileManager.ambienteAperto) {
            setDefaultValues();
            translateTemp(screenX, screenY, g);
            g.setColor(Color.RED);
            g.fillRect(0, 0, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE);
            untranslateTemp(screenX, screenY, g);
        }else{
            if (isVisible(Defines.CAMERA)) {
                g.setColor(Color.RED);
                translate(Defines.CAMERA.getScreenX(), Defines.CAMERA.getScreenY(), g);
                g.fillRect(0, 0, Defines.GRANDEZZA_CASELLE, Defines.GRANDEZZA_CASELLE);
                untranslate(Defines.CAMERA.getScreenX(), Defines.CAMERA.getScreenY(), g);
            }
        }
    }   
}
