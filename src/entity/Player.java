package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.GamePanel;
import game.GestioneTasti;
import utils.Defines;
import utils.Spritesheet;
import utils.SpritesheetPlayer;

public class Player extends Entity {
    private final GestioneTasti gestioneTasti;

    private final int screenX;
    private final int screenY;

    boolean isAlive;
    boolean isAliveAnimation = false;

    SpritesheetPlayer moving;
    SpritesheetPlayer notMoving;
    Spritesheet dying;

    public Player(GestioneTasti g2) {
        gestioneTasti = g2;

        screenX = Defines.SCREEN_WIDTH / 2 - Defines.GRANDEZZA_CASELLE / 2;
        screenY = Defines.SCREEN_HEIGHT / 2 - Defines.GRANDEZZA_CASELLE / 2;

        areaCollisione = new Rectangle();
        areaCollisione.x = 10;
        areaCollisione.y = 20;
        areaCollisione.width = Defines.GRANDEZZA_CASELLE - 20;
        areaCollisione.height = Defines.GRANDEZZA_CASELLE - 24;
        setDefaultValues();
    }

    private void setDefaultValues() {
        worldX = Defines.GRANDEZZA_CASELLE * 8;
        worldY = Defines.GRANDEZZA_CASELLE * 5;
        isAlive = true;
        setDirezione("su");
        getPlayerImage();
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    private boolean getPremuto(String key) {
        return gestioneTasti.getPremuto(key);
    }

    private void spriteCounter(int n, int wait) {
        this.spriteCounter++;
        if (this.spriteCounter > wait / delta) {
            if (spriteNum < n) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            this.spriteCounter = 0;
        }
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

    private void getPlayerImage() {
        moving = new SpritesheetPlayer(4, 5, 3, 4, "finn.png");
        notMoving = new SpritesheetPlayer(2, 2, 0, 1, "finn.png");
        dying = new Spritesheet(6, 0, "/res/player/","FinnDeath.png");
    }

    private boolean lontanoDaiBordi() {
        boolean stato = false;
        int offset = 2;
        if (getCol() < offset || getCol() > GamePanel.getMaxWorldCol() - 2 - offset) {
            stato = true;
        }
        if (getRow() < offset || getRow() > GamePanel.getMaxWorldRow() - 2 - offset) {
            stato = true;
        }
        return stato;
    }

    public void update() {
        boolean w = getPremuto("W");
        boolean s = getPremuto("S");
        boolean d = getPremuto("D");
        boolean a = getPremuto("A");

        boolean shift = getPremuto("SHIFT");

        boolean k = getPremuto("K");
        boolean e = getPremuto("E");
        if (isAlive && k) {
            isAlive = false;
            isAliveAnimation=false;
        }

        if (e && !isAlive) {
            spriteCounter(0, 0);
            isAlive=true;
            isAliveAnimation=true;
        }

        if (!isAlive && !isAliveAnimation) {
            spriteCounter(5, 10);
            if (spriteNum==5) {
                spriteNum=4;
            }
            
        }

        if (isAliveAnimation) {
            
        }
        
        if (isAlive) {
            if (shift) {
                if (!lontanoDaiBordi()) {
                    setSpeed(4, 1.5);
                }else setSpeed(4, 1);
                
            }else if (!shift) {
                setSpeed(4, 1);
            }
                
            
    
            if (!w && !s && !d && !a) {
                spriteCounter(1, 27);
                switch (getDirezione()) {
                    case "giu":
                        spostaY("fermoGiu", "");
                        return;
                    case "su":
                        spostaY("fermoSu", "");
                        return;
                    case "destra":
                        spostaX("fermoDestra", "");
                        return;
                    case "sinistra":
                        spostaX("fermoSinistra", "");
                        return;
                    default:
                        return;
                }
            }
    
            if (w || s || d || a) {
                spriteCounter(3, 8);
                if (a && d) {
                    setDirezione("fermoGiu");
                    if (w)
                        setDirezione("su");
                    if (s)
                        setDirezione("giu");
                    controllaCollisioniY(w, s);
                } else if (w && s) {
                    setDirezione("fermoGiu");
                    if (d)
                        setDirezione("destra");
                    if (a)
                        setDirezione("sinistra");
                    controllaCollisioniX(a, d);
                } else {
                    if (w)
                        setDirezione("su");
                    if (s)
                        setDirezione("giu");
                    controllaCollisioniY(w, s);
                    if (d)
                        setDirezione("destra");
                    if (a)
                        setDirezione("sinistra");
                    // CONTROLLA COLLISIONI
                    controllaCollisioniX(a, d);
                }
            }
        }
    }

    public void controllaCollisioniX(boolean a, boolean d) {
        inCollisione = false;
        Defines.GAME_PANEL.collisioni.controllaCasella(this);
        if (!inCollisione) {
            if (d)
                spostaX("destra", "aggiungi");
            if (a)
                spostaX("sinistra", "sottrai");

        }
    }

    public void controllaCollisioniY(boolean w, boolean s) {
        inCollisione = false;
        Defines.GAME_PANEL.collisioni.controllaCasella(this);
        if (!inCollisione) {
            if (w)
                spostaY("su", "sottrai");
            if (s)
                spostaY("giu", "aggiungi");
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        if (isAlive) {
            switch (getDirezione()) {
                case "su":
                    image = moving.getUp().getSpriteSheet(spriteNum);
                    break;
                case "giu":
                    image = moving.getDown().getSpriteSheet(spriteNum);
                    break;
                case "destra":
                    image = moving.getRight().getSpriteSheet(spriteNum);
                    break;
                case "sinistra":
                    image = moving.getLeft().getSpriteSheet(spriteNum);
                    break;
                case "fermoDestra":
                    if (spriteNum == 1)
                        image = notMoving.getRight().getSpriteSheet(0);
                    else
                        image = notMoving.getRight().getSpriteSheet(1);
                    break;
                case "fermoSinistra":
                    if (spriteNum == 1)
                        image = notMoving.getLeft().getSpriteSheet(0);
                    else
                        image = notMoving.getLeft().getSpriteSheet(1);
                    break;
                case "fermoSu":
                    if (spriteNum == 1)
                        image = notMoving.getUp().getSpriteSheet(0);
                    else
                        image = notMoving.getUp().getSpriteSheet(1);
                    break;
                case "fermoGiu":
                    if (spriteNum == 1)
                        image = notMoving.getDown().getSpriteSheet(0);
                    else
                        image = notMoving.getDown().getSpriteSheet(1);
                    break;
    
            }
        }else{
            image = dying.getSpriteSheet(spriteNum);
        }
        g.drawImage(image, screenX - Defines.GRANDEZZA_CASELLE / 2, screenY - Defines.GRANDEZZA_CASELLE / 2,
                Defines.GRANDEZZA_CASELLE * 2, Defines.GRANDEZZA_CASELLE * 2, null);
        // g.drawRect(screenX + areaCollisione.x, screenY + areaCollisione.y,
        // areaCollisione.width, areaCollisione.height);
        ;
    }
}
