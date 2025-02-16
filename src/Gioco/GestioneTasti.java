package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener{

    private boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto;

    public GestioneTasti(){}

    private void setPremuto(String key){
        switch (key) {
            case "W":
                this.suPremuto=true;
                break;
            case "S":
                this.giuPremuto=true;
                break;
            case "D":
                this.destraPremuto=true;
                break;
            case "A":
                this.sinistraPremuto=true;
                break;
        }
    }
    private void setRilasciato(String key){
        switch (key) {
            case "W":
                this.suPremuto=false;
                break;
            case "S":
                this.giuPremuto=false;
                break;
            case "D":
                this.destraPremuto=false;
                break;
            case "A":
                this.sinistraPremuto=false;
                break;
        }
    }

    public boolean getPremuto(String key){
        switch (key) {
            case "W":
                return this.suPremuto;
            case "S":
                return this.giuPremuto;
            case "D":
                return this.destraPremuto;
            case "A":
                return this.sinistraPremuto;
            default:
             return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                setPremuto("W");
                System.out.println("W premuto");
                break;
            case KeyEvent.VK_S:
                setPremuto("S");
                System.out.println("S premuto");
                break;
            case KeyEvent.VK_D:
                setPremuto("D");
                System.out.println("D premuto");
                break;
            case KeyEvent.VK_A:
                setPremuto("A");
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
                setRilasciato("W");
                System.out.println("W rilasciato");
                break;
            case KeyEvent.VK_S:
                setRilasciato("S");
                System.out.println("S rilasciato");
                break;
            case KeyEvent.VK_D:
                setRilasciato("D");
                System.out.println("D rilasciato");
                break;
            case KeyEvent.VK_A:
                setRilasciato("A");
                System.out.println("A rilasciato");
                break;

            default:
                break;
        }
    }

}   
