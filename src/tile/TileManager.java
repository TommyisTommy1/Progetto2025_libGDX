package tile;

import game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import mp3player.MP3Player;
import utils.Camera;
import utils.Defines;

public class TileManager {

    public Tileset tileset;

    private Camera camera;

    private Casella[] uscita, spawn;
    public int[][] n;
    public int misurax, misuray;
    
    private static int[] mappe = {1, 2};
    private static int currentMappa = -1;
    boolean flag = false;
    

    int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
    public TileManager() {
        tileset = new Tileset();
        
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

    /*private void getTileImage() {
        tile[tileCount].image = loadTileImage("stonebrick.png"); tile[tileCount-1].collision = true;
        tile[tileCount].image = loadTileImage("mattons.png"); tile[tileCount-1].collision = false;
        path = new Spritesheet(15, 0, "/res/tile/", "path.png");
        grass = new Spritesheet(10, 0, "/res/tile/", "grass.png");
        for (int index = 0; index < path.getNum(); index++) {
            tile[tileCount].image=path.getSpriteSheet(index);
            tileCount++;
        }
        for (int index = 0; index < grass.getNum(); index++) {
            tile[tileCount].image=grass.getSpriteSheet(index);
            tileCount++;
        }
        
    }*/

    /*private BufferedImage loadTileImage(String percorso) {
        try {
            tileCount++;
            return ImageIO.read(getClass().getResourceAsStream("/res/tile/".concat(percorso))); //restituisce l'immagine richiesta
        } catch (Exception e) {
            System.err.println("Tile non trovato: " + percorso);
            return null;
        }
    }*/

    public void setCurrentMap(int map){
        currentMappa = map; //autoesplicativo
    }

    public void setPosizionePlayer(int x, int y) {
        Defines.PLAYER.setWorldX(x*grandezzaCaselle); //autoesplicativo
        Defines.PLAYER.setWorldY(y*grandezzaCaselle);
    }
    
    public boolean isInUscita(int x, int y, int i) { //controlla se il player è in una casella di uscita
        boolean flag = false;
        if (x == uscita[i].getCol() && y == uscita[i].getRow()) {
            flag = true;
        }
        return flag;
    }

    public void draw(Graphics2D g) {
        int playerCol = Defines.PLAYER.getWorldX() / grandezzaCaselle;
        int playerRow = Defines.PLAYER.getWorldY() / grandezzaCaselle;
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

                camera = new Camera(); // crea una nuova telecamera
                camera.setCameraCasella(col, row); //setta la posizione dell'oggetto da visualizzare
                int tileNum = n[col][row];
                
                if (isVisible(camera)) {
                    
                    g.drawImage(tileset.getTile(tileNum), camera.getScreenX(), camera.getScreenY(), grandezzaCaselle, grandezzaCaselle, null); //disegna il tile enlla posizione calcolata
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
            case 1 -> {
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt"); 
                Defines.GAME_PANEL.setBackground(Color.black); //cambia il colore dello sfondo
            }
            case 2 -> {
                loadMap("map02.txt", "misureMap02.txt", "uscita02.txt", "spawn02.txt");
                Defines.GAME_PANEL.setBackground(Color.gray);
            }
            default -> {
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt");
                Defines.GAME_PANEL.setBackground(Color.black);
            }
        }
        //carica la mappa in base alla mappa attuale
        updateMusic();
        //updateShader();
        if (uscitaIndex >= 0 && uscitaIndex < spawn.length)
            setPosizionePlayer(spawn[uscitaIndex].getCol(), spawn[uscitaIndex].getRow()); //sposta il player nella posizione di spawn
    }

    private boolean isVisible(Camera camera) { //controlla se il tile è visibile
        
        return camera.getCameraWorldX() + grandezzaCaselle > Defines.PLAYER.getWorldX() - Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldX() - grandezzaCaselle < Defines.PLAYER.getWorldX() + Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldY() + grandezzaCaselle > Defines.PLAYER.getWorldY() - Defines.PLAYER.getScreenY() &&
            camera.getCameraWorldY() - grandezzaCaselle < Defines.PLAYER.getWorldY() + Defines.PLAYER.getScreenY();
    }






    public static void updateMusic() {
        System.out.println("Updating music for map: " + currentMappa);
        try {
            if (Defines.BGMUSIC_PLAYER != null && Defines.BGMUSIC_PLAYER.isAlive()) {
                Defines.MP3_PLAYER_SETTER.stopPlayer();
                Defines.BGMUSIC_PLAYER.interrupt();
                try {
                    Defines.BGMUSIC_PLAYER.join(250);
                } catch (InterruptedException e) {
                    System.err.println("Interrupted while waiting for thread to join: " + e.getMessage());
                }
            }

            Defines.MP3_PLAYER_SETTER = new MP3Player(currentMappa - 1);
            Defines.BGMUSIC_PLAYER = new Thread(Defines.MP3_PLAYER_SETTER);
            
            try {
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
    /* 
    public static void updateShader ()
    {
        if (Defines.SHADER_Thread.isAlive() && Defines.SHADERS != null) 
        {
            try {
                Defines.SHADER_Thread.interrupt();
                Defines.SHADER_Thread.join(50);
            } catch (Exception e) {
                System.err.println("Error stopping shader thread: " + e.getMessage());
            }
        }
        String[] mapShader = new String[]{"none", "src/res/shaders/sw.png"};
        Defines.SHADERS = new Shaders(Defines.GAME_PANEL, mapShader[currentMappa - 1]);
        Defines.SHADER_Thread = new Thread(Defines.SHADERS);
        try {
            Defines.SHADER_Thread.start();
            System.out.println("Shader changed successfully");
            System.out.println("Shader path: " + mapShader[currentMappa - 1]);
        } catch (Exception e) {
            System.err.println("Error starting new shader: " + e.getMessage());
        }
        Defines.MAINFRAME.add(Defines.GAME_PANEL);
    }
    */
}
