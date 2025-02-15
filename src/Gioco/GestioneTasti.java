package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener{

    public boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto;

    public GestioneTasti(){}

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code==KeyEvent.VK_W) {
            suPremuto = true;
            System.out.println("W premuto");
        }
        if (code==KeyEvent.VK_S) {
            giuPremuto = true;
            System.out.println("S premuto");
        }
        if (code==KeyEvent.VK_D) {
            destraPremuto = true;
            System.out.println("D premuto");
        }
        if (code==KeyEvent.VK_A) {
            sinistraPremuto = true;
            System.out.println("Awdw premuto");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code==KeyEvent.VK_W) {
            suPremuto = false;
        }
        if (code==KeyEvent.VK_S) {
            giuPremuto = false;
        }
        if (code==KeyEvent.VK_D) {
            destraPremuto = false;
        }
        if (code==KeyEvent.VK_A) {
            sinistraPremuto = false;
        }
    }

}   
