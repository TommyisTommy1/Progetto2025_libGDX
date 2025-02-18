package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener {

    private boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto, interagisci, sprint;

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
            case "E":
                this.interagisci = true;
                break;
            case "SHIFT":
                this.sprint = true;
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
            case "E":
                this.interagisci = false;
                break;
            case "SHIFT":
                this.sprint = false;
                break;
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
            case "E":
                return this.interagisci;
            case "SHIFT":
                return this.sprint;
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
                setPremuto("W");
                break;
            case KeyEvent.VK_S:
                setPremuto("S");
                break;
            case KeyEvent.VK_D:
                setPremuto("D");
                break;
            case KeyEvent.VK_A:
                setPremuto("A");
                break;
            case KeyEvent.VK_E:
                setPremuto("E");
                break;
            case KeyEvent.VK_SHIFT:
                    setPremuto("SHIFT");
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
                break;
            case KeyEvent.VK_S:
                setRilasciato("S");
                break;
            case KeyEvent.VK_D:
                setRilasciato("D");
                break;
            case KeyEvent.VK_A:
                setRilasciato("A");
                break;
            case KeyEvent.VK_E:
                setRilasciato("E");
                break;
            case KeyEvent.VK_SHIFT:
                setRilasciato("SHIFT");
                break;
            default:
                break;
        }
    }

}
