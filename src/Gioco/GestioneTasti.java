package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener{

    public boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code==KeyEvent.VK_W) {
            suPremuto = true;
        }
        if (code==KeyEvent.VK_S) {
            giuPremuto = true;
        }
        if (code==KeyEvent.VK_D) {
            destraPremuto = true;
        }
        if (code==KeyEvent.VK_A) {
            sinistraPremuto = true;
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
