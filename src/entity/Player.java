package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Gioco.GestioneTasti;
import utils.Defines;

public class Player extends Entity {
    private final GestioneTasti gestioneTasti;

    private final int screenX;
    private final int screenY;

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
        speed = 4;
        setDirezione("su");
        getPlayerImage();
    }

    private BufferedImage loadPlayerImage(int x, int y) {
        String percorso = new String("Finn.png");
        x=x*32;
        y=y*32;
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/player/".concat(percorso))).getSubimage(x, y, 32, 32);
        } catch (IOException e) {
            System.err.println("Immagine non trovata in: " + percorso);
            return null;
        }
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
        if (this.spriteCounter > wait) {
            if (spriteNum<n) {
                spriteNum++;
            }else{
                spriteNum=0;
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
        fermo[0] = loadPlayerImage(0, 0);
        fermo[1] = loadPlayerImage(1, 0);
        fermo[2] = loadPlayerImage(0, 2);
        fermo[3] = loadPlayerImage(1, 2);
        fermo[4] = loadPlayerImage(0, 1);
        fermo[5] = loadPlayerImage(1, 1);
        fermo[6] = flipImmagine(loadPlayerImage(0, 1));
        fermo[7] = flipImmagine(loadPlayerImage(1, 1));
        su[0] = loadPlayerImage(0, 5);
        su[1] = loadPlayerImage(1, 5);
        su[2] = loadPlayerImage(2, 5);
        su[3] = loadPlayerImage(3, 5);
        giu[0] = loadPlayerImage(0, 3);
        giu[1] = loadPlayerImage(1, 3);
        giu[2] = loadPlayerImage(2, 3);
        giu[3] = loadPlayerImage(3, 3);
        destra[0] = loadPlayerImage(0, 4);
        destra[1] = loadPlayerImage(1, 4);
        destra[2] = loadPlayerImage(2, 4);
        destra[3] = loadPlayerImage(3, 4);
        sinistra[0] = flipImmagine(destra[0]);
        sinistra[1] = flipImmagine(destra[1]);
        sinistra[2] = flipImmagine(destra[2]);
        sinistra[3] = flipImmagine(destra[3]);
    }

    public void update() {
        boolean w = getPremuto("W");
        boolean s = getPremuto("S");
        boolean d = getPremuto("D");
        boolean a = getPremuto("A");

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
                    spostaX("fermoDestra","");
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

    public void controllaCollisioni(boolean w, boolean s, boolean a, boolean d) {
        inCollisione = false;
        Defines.GAME_PANEL.collisioni.controllaCasella(this);
        if (!inCollisione) {
            
            if (w)
                spostaY(getDirezione(), "sottrai");
            if (s)
                spostaY(getDirezione(), "aggiungi");
            if (d)
                spostaX(getDirezione(), "aggiungi");
            if (a)
                spostaX(getDirezione(), "sottrai");
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
    public BufferedImage flipImmagine(BufferedImage image){
        BufferedImage immagineFlippata = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = immagineFlippata.createGraphics();

        g.drawImage(image, image.getWidth(), 0 , -image.getWidth(), image.getHeight(), null);
        g.dispose();
        return immagineFlippata;
    }
    public void draw(Graphics2D g) {
        BufferedImage image = null;
        switch (getDirezione()) {
            case "su":
                image = su[spriteNum];
                break;
            case "giu":
                image = giu[spriteNum];
                break;
            case "destra":
                image = destra[spriteNum];
                break;
            case "sinistra":
                image = sinistra[spriteNum];
                break;
            case "fermoDestra":
                if (spriteNum == 1) image = fermo[4];
                else image = fermo[5];
                break;
            case "fermoSinistra":
                if (spriteNum == 1) image = flipImmagine(fermo[4]);
                else image = flipImmagine(fermo[5]);
                break;
            case "fermoSu":
                if (spriteNum == 1) image = fermo[2];
                else image = fermo[3];
                break;
            case "fermoGiu":
                if (spriteNum == 1) image = fermo[0];
                else image = fermo[1];
                break;
            
                
        }
        g.drawImage(image, screenX-Defines.GRANDEZZA_CASELLE/2, screenY-Defines.GRANDEZZA_CASELLE/2, Defines.GRANDEZZA_CASELLE*2, Defines.GRANDEZZA_CASELLE*2, null);
        //g.drawRect(screenX + areaCollisione.x, screenY + areaCollisione.y, areaCollisione.width, areaCollisione.height);
        ;
    }
}
