package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestioneTasti implements KeyListener {

    private boolean suPremuto, giuPremuto, destraPremuto, sinistraPremuto, interagisci, sprint;

    private boolean kill; //temporaneo

    public GestioneTasti() {
    }

    private void setPremuto(String key) {
        switch (key) {
            case "W" -> this.suPremuto = true;
            case "S" -> this.giuPremuto = true;
            case "D" -> this.destraPremuto = true;
            case "A" -> this.sinistraPremuto = true;
            case "E" -> this.interagisci = true;
            case "SHIFT" -> this.sprint = true;
            case "K" -> this.kill = true;
        }
    }

    public void setRilasciato(String key) {
        switch (key) {
            case "W" -> this.suPremuto = false;
            case "S" -> this.giuPremuto = false;
            case "D" -> this.destraPremuto = false;
            case "A" -> this.sinistraPremuto = false;
            case "E" -> this.interagisci = false;
            case "SHIFT" -> this.sprint = false;
            case "K" -> this.kill = false;
        }
    }



    public boolean getPremuto(String key) {
        switch (key) {
            case "W" -> {
                return this.suPremuto;
            }
            case "S" -> {
                return this.giuPremuto;
            }
            case "D" -> {
                return this.destraPremuto;
            }
            case "A" -> {
                return this.sinistraPremuto;
            }
            case "E" -> {
                return this.interagisci;
            }
            case "SHIFT" -> {
                return this.sprint;
            }
            case "K" -> {
                return this.kill;
            }
            default -> {
            }
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
            case KeyEvent.VK_W -> setPremuto("W");
            case KeyEvent.VK_S -> setPremuto("S");
            case KeyEvent.VK_D -> setPremuto("D");
            case KeyEvent.VK_A -> setPremuto("A");
            case KeyEvent.VK_E -> setPremuto("E");
            case KeyEvent.VK_SHIFT -> setPremuto("SHIFT");
            case KeyEvent.VK_K -> setPremuto("K");
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> setRilasciato("W");
            case KeyEvent.VK_S -> setRilasciato("S");
            case KeyEvent.VK_D -> setRilasciato("D");
            case KeyEvent.VK_A -> setRilasciato("A");
            case KeyEvent.VK_E -> setRilasciato("E");
            case KeyEvent.VK_SHIFT -> setRilasciato("SHIFT");
            case KeyEvent.VK_K -> setRilasciato("K");
            default -> {
            }
        }
    }

}
