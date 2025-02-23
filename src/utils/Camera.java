package utils;

public class Camera {
    int x, y;

    int playerWorldX = Defines.PLAYER.getWorldX();
    int playerWorldY = Defines.PLAYER.getWorldY();

    int playerScreenX = Defines.PLAYER.getScreenX();
    int playerScreenY = Defines.PLAYER.getScreenY();

    public Camera() {
    }
    
    public void setCameraCasella(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCameraCasellaX() {
        return x;
    }
    public int getCameraCasellaY() {
        return y;
    }

    public int getCameraWorldX() {
        return x * Defines.GRANDEZZA_CASELLE;
    }

    public int getCameraWorldY() {
        return y * Defines.GRANDEZZA_CASELLE;
    }

    public int getScreenX() {
        return getCameraWorldX()-playerWorldX+playerScreenX;
    }

    public int getScreenY() {
        return getCameraWorldY()-playerWorldY+playerScreenY;
    }
}
