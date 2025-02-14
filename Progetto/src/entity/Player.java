package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import Gioco.GamePanel;
import Gioco.GestioneTasti;

public class Player extends Entity{
        GamePanel panGioco;
        GestioneTasti gestioneTasti;

    public Player(GamePanel g1, GestioneTasti g2){
        this.panGioco = g1;
        this.gestioneTasti = g2;

        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100; 
        speed = 4;
    }
    public void update(){
        if (gestioneTasti.suPremuto == true) {
            y -= speed;
        }
        if (gestioneTasti.giuPremuto == true) {
            y += speed;
        }
        if (gestioneTasti.destraPremuto == true) {
            x += speed;
        }
        if (gestioneTasti.sinistraPremuto == true) {
            x -= speed;
        }
    }
    public void draw(Graphics2D g){
        g.setColor(Color.white);
        g.fillRect(x, y, panGioco.grandezzaCaselle, panGioco.grandezzaCaselle);
    }
}
