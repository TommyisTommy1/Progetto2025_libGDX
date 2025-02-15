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
        switch (code) {
            case KeyEvent.VK_W:
                suPremuto = true;
                System.out.println("W premuto");
                break;
            case KeyEvent.VK_S:
                giuPremuto = true;
                System.out.println("S premuto");
                break;
            case KeyEvent.VK_D:
                destraPremuto = true;
                System.out.println("D premuto");
                break;
            case KeyEvent.VK_A:
                sinistraPremuto = true;
                System.out.println("A premuto");
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                suPremuto = false;
                System.out.println("W rilasciato");
                break;
            case KeyEvent.VK_S:
                giuPremuto = false;
                System.out.println("S rilasciato");
                break;
            case KeyEvent.VK_D:
                destraPremuto = false;
                System.out.println("D rilasciato");
                break;
            case KeyEvent.VK_A:
                sinistraPremuto = false;
                System.out.println("A rilasciato");
                break;

            default:
                break;
        }
    }

}   
