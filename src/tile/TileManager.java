package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import entity.Player;
import game.GamePanel;
import utils.Camera;
import utils.Defines;

public class TileManager {
    private Camera camera;
    public Tile[] tile;
    private Casella[] uscita, spawn;
    public int[][] n;
    public int misurax, misuray;
    
    private int[] mappe = {1, 2};
    private int currentMappa = -1;
    boolean flag = false;
    

    int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
    public TileManager() {
        tile = new Tile[100];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        getTileImage();
    }

    private void loadMap(String mappa, String misure, String uscite, String entrate) {
        try {
            misurax = misuray = 0;
            uscita = loadCaselle(uscite);   //carica le caselle delle uscite e entrate
            spawn = loadCaselle(entrate); 
            
            InputStream is1 = getClass().getResourceAsStream("/res/map/".concat(misure)); 
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1)); 
            String[] numbers = br1.readLine().split(" ");   //legge la prima riga del file e la salva in un array di stringhe

            misurax = Integer.parseInt(numbers[0]); //prende la grandezza della mappa dal file
            misuray = Integer.parseInt(numbers[1]);
            
            Defines.GAME_PANEL.setMaxWorld(misurax, misuray); //grandezza mappa in colonne e righe
            
            n = new int[misurax][misuray]; //crea un array con la grandezza della mappa
            
            is1 = getClass().getResourceAsStream("/res/map/".concat(mappa)); //carica la mappa
            br1 = new BufferedReader(new InputStreamReader(is1));
            
            for (int row = 0; row < misuray; row++) {
                numbers = br1.readLine().split(" "); //legge la riga del file  successiva e la salva in un array di stringhe
                for (int col = 0; col < misurax; col++) {
                    n[col][row] = Integer.parseInt(numbers[col]); //salva il tipo di casella nell'array
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nel caricamento della mappa: " + mappa);
        }
    }

    private Casella[] loadCaselle(String filePath) throws Exception {

        InputStream is = getClass().getResourceAsStream("/res/map/".concat(filePath)); 
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String[] numbers = br.readLine().split(" "); //legge la prima riga come prima

        Casella[] caselle = new Casella[numbers.length / 2];

        for (int i = 0; i < numbers.length; i += 2) {
            caselle[i / 2] = new Casella(Integer.parseInt(numbers[i]), Integer.parseInt(numbers[i + 1]));
        }
        return caselle;
    }

    private void getTileImage() {
        tile[0].image = loadTileImage("grass02.png"); //abbastanza autoesplicativo
        tile[1].image = loadTileImage("grass01.png");
        tile[2].image = loadTileImage("water_top_left.png"); tile[2].collision = true;
        tile[3].image = loadTileImage("water_top.png"); tile[3].collision = true;
        tile[4].image = loadTileImage("water_top_right.png"); tile[4].collision = true;
        tile[5].image = loadTileImage("stonebrick.png"); tile[5].collision = true;
        tile[6].image = loadTileImage("top_left.png"); tile[6].collision = true;
        tile[7].image = loadTileImage("top_right.png"); tile[7].collision = true;
        tile[8].image = loadTileImage("bottom_left.png"); tile[8].collision = true;
        tile[9].image = loadTileImage("bottom_right.png"); tile[9].collision = true;
        tile[11].image = loadTileImage("mattons.png"); tile[11].collision = false;
    }

    private BufferedImage loadTileImage(String percorso) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso))); //restituisce l'immagine richiesta
        } catch (Exception e) {
            System.err.println("Tile non trovato: " + percorso);
            return null;
        }
    }

    public void setCurrentMap(int map){
        currentMappa = map; //autoesplicativo
    }

    public void setPosizionePlayer(int x, int y) {
        Defines.PLAYER.setWorldX(x*Defines.GRANDEZZA_CASELLE); //autoesplicativo
        Defines.PLAYER.setWorldY(y*Defines.GRANDEZZA_CASELLE);
    }
    
    public boolean isInUscita(int x, int y, int i) { //controlla se il player è in una casella di uscita
        boolean flag = false;
        if (x == uscita[i].getCol() && y == uscita[i].getRow()) {
            flag = true;
        }
        return flag;
    }

    public void draw(Graphics2D g) {
        int playerCol = Player.getWorldX() / grandezzaCaselle;
        int playerRow = Player.getWorldY() / grandezzaCaselle;
        boolean e = GamePanel.keyH.getPremuto("E");

        if (currentMappa == -1) {
            setCurrentMap(2);
            cambiaMappa(currentMappa);
            setPosizionePlayer(7, 7);
        }

        for (int i = 0; i < uscita.length; i++) {
            if (isInUscita(playerCol, playerRow, i) && e && !flag) {
                cambiaMappa(i);  // Passiamo l'indice dell'uscita usata
                flag=true;
                break;
            }
            if (!e && flag==true) {
                flag=false; // Resetta il flag quando il tasto non è premuto
            }
        }
        

        for (int row = 0; row < misuray; row++) {
            for (int col = 0; col < misurax; col++) {

                camera = new Camera();
                camera.setCameraCasella(col, row);
                int tileNum = n[col][row];
                
                if (isVisible(camera)) {
                    g.drawImage(tile[tileNum].image, camera.getScreenX(), camera.getScreenY(), grandezzaCaselle, grandezzaCaselle, null); //disegna il tile enlla posizione calcolata
                }
            }
        }
    }

    private void cambiaMappa(int uscitaIndex) {
        if (currentMappa == mappe[mappe.length - 1]) {
            currentMappa = mappe[0];
        } else {
            currentMappa++;
        }
        System.out.println("Mappa  " + currentMappa);

        switch (currentMappa) { //carica la mappa in base alla mappa attuale
            case 1:
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt"); 
                Defines.GAME_PANEL.setBackground(Color.black); //cambia il colore dello sfondo
                break;
            case 2:
                loadMap("map02.txt", "misureMap02.txt", "uscita02.txt", "spawn02.txt");
                Defines.GAME_PANEL.setBackground(Color.gray);
                break;
            default:
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt");
                Defines.GAME_PANEL.setBackground(Color.black);
                break;
        }
        //updateMusic();
        if (uscitaIndex >= 0 && uscitaIndex < spawn.length)
            setPosizionePlayer(spawn[uscitaIndex].getCol(), spawn[uscitaIndex].getRow()); //sposta il player nella posizione di spawn
    }

    private boolean isVisible(Camera camera) { //controlla se il tile è visibile
        
        return camera.getCameraWorldX() + grandezzaCaselle > Player.getWorldX() - Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldX() - grandezzaCaselle < Player.getWorldX() + Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldY() + grandezzaCaselle > Player.getWorldY() - Defines.PLAYER.getScreenY() &&
            camera.getCameraWorldY() - grandezzaCaselle < Player.getWorldY() + Defines.PLAYER.getScreenY();
    }






    public void updateMusic() {
        System.out.println("Updating music for map: " + currentMappa);
        try {
            if (Defines.BGMUSIC_PLAYER != null && Defines.BGMUSIC_PLAYER.isAlive()) {
                System.out.println("Stopping previous music");
                try {
                    Defines.MP3_PLAYER_SETTER.stopPlayer(true);
                } catch (Exception e) {
                    System.err.println("Error stopping player: " + e.getMessage());
                }
                Defines.BGMUSIC_PLAYER.interrupt();
                try {
                    Defines.BGMUSIC_PLAYER.join(250); // Wait for up to 1 second
                } catch (InterruptedException e) {
                    System.err.println("Interrupted while waiting for thread to join: " + e.getMessage());
                }
            }
    
            System.out.println("Changing track to: " + (currentMappa - 1));
            try {
                Defines.MP3_PLAYER_SETTER.changeTrack(currentMappa - 1);
            } catch (Exception e) {
                System.err.println("Error changing track: " + e.getMessage());
                return; // Exit the method if we can't change the track
            }
    
            System.out.println("Creating new music thread");
            Defines.BGMUSIC_PLAYER = new Thread(Defines.MP3_PLAYER_SETTER);
    
            System.out.println("Starting new music");
            try {
                Defines.MP3_PLAYER_SETTER.stopPlayer(false);
                Defines.BGMUSIC_PLAYER.start();
                System.out.println("Music changed successfully");
            } catch (Exception e) {
                System.err.println("Error starting new music: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in updateMusic: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
