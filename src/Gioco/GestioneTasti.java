package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener {

    private boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto, cambioMappa;

    public GestioneTasti() {
    }

    private void setPremuto(String key) {
        switch (key) {
            case "W":
                this.suPremuto = true;
                break;
            case "S":
                this.giuPremuto = true;
                break;
            case "D":
                this.destraPremuto = true;
                break;
            case "A":
                this.sinistraPremuto = true;
                break;
            case "F":
                this.cambioMappa = true;
                break;
        }
    }

    public void setRilasciato(String key) {
        switch (key) {
            case "W":
                this.suPremuto = false;
                break;
            case "S":
                this.giuPremuto = false;
                break;
            case "D":
                this.destraPremuto = false;
                break;
            case "A":
                this.sinistraPremuto = false;
                break;
            case "F":
                this.cambioMappa = false;
                break;
        }
    }

    
    public boolean getRilasciato(String key) {
        switch (key) {
            case "W":
                return suPremuto;
            case "S":
                return giuPremuto;
            case "D":
                return destraPremuto;
            case "A":
                return sinistraPremuto;
            case "F":
                return cambioMappa;
            default:
                return false;
        }
    }


    public boolean getPremuto(String key) {
        switch (key) {
            case "W":
                return this.suPremuto;
            case "S":
                return this.giuPremuto;
            case "D":
                return this.destraPremuto;
            case "A":
                return this.sinistraPremuto;
            case "F":
                return this.cambioMappa;
            default:
                break;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:

                if (!getPremuto("W"))
                    System.out.println("W premuto");
                setPremuto("W");

                break;
            case KeyEvent.VK_S:

                if (!getPremuto("S"))
                    System.out.println("S premuto");
                setPremuto("S");

                break;
            case KeyEvent.VK_D:

                if (!getPremuto("D"))
                    System.out.println("D premuto");
                setPremuto("D");

                break;
            case KeyEvent.VK_A:

                if (!getPremuto("A"))
                    System.out.println("A premuto");
                setPremuto("A");

                break;
            case KeyEvent.VK_F:
                
                if (!getPremuto("F"))
                    System.out.println("F premuto");
                setPremuto("F");
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
            case KeyEvent.VK_F:
                setRilasciato("F");
                System.out.println("F rilasciato");
                break;

            default:
                break;
        }
    }

}
