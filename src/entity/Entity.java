package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public String direction;
    public BufferedImage su1, su2, giu1, giu2, destra1, destra2, sinistra1, sinistra2, fermoSu, fermoGiu, fermoDestra,
            fermoSinistra;
}
