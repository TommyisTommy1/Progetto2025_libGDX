package tile;

import game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import utils.Camera;
import utils.Defines;

public class TileManager {

    public Tileset tileset;

    private Casella[] uscita, spawn;
    public int[][] n;
    public int misurax, misuray;
    
    private static int[] mappe = {1, 2};
    private static int currentMappa = -1;
    public static boolean ambienteAperto;
    boolean flag = false;
    
    int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;

    //Costruttore

    public TileManager() {
        tileset = new Tileset();
    }

    //Culling

    private boolean isVisible(Camera camera) { //controlla se il tile è visibile
        
        return camera.getCameraWorldX() + grandezzaCaselle > Defines.PLAYER.getWorldX() - Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldX() - grandezzaCaselle < Defines.PLAYER.getWorldX() + Defines.PLAYER.getScreenX() &&
            camera.getCameraWorldY() + grandezzaCaselle > Defines.PLAYER.getWorldY() - Defines.PLAYER.getScreenY() &&
            camera.getCameraWorldY() - grandezzaCaselle < Defines.PLAYER.getWorldY() + Defines.PLAYER.getScreenY();
    }

    //Setta la mappa corrente

    public void setCurrentMap(int map){
        currentMappa = map; //autoesplicativo
    }

    //Cambia posizione player

    public void setPosizionePlayer(int x, int y) {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        Defines.PLAYER.setWorldX(x*grandezzaCaselle); //autoesplicativo
        Defines.PLAYER.setWorldY(y*grandezzaCaselle);
    }

    //Controlla se il player è vicino un uscita
     
    public boolean isInUscita(int x, int y, int i) { //controlla se il player è in una casella di uscita
        boolean isInUscita = false;
        if (x == uscita[i].getCol() && y == uscita[i].getRow()) {
            isInUscita = true;
        }
        return isInUscita;
    }

    //Legge la mappa dal file di testo

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

    //Selezione mappe

    private void cambiaMappa(int uscitaIndex) {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        if (currentMappa == mappe[mappe.length - 1]) { //Se la mappa è quella finale allora torni alla prima mappa
            currentMappa = mappe[0];
        } else {
            currentMappa++; //Se no vai alla successiva
        }
        System.out.println("Mappa  " + currentMappa);

        switch (currentMappa) { //carica la mappa in base alla mappa attuale
            case 1 -> {
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt"); 
                Defines.GAME_PANEL.setBackground(Color.black); //cambia il colore dello sfondo
                ambienteAperto=true;
                GamePanel.SCALA=3;
                Defines.PLAYER.setSpeed(4);
                Defines.GRANDEZZA_CASELLE = (int) Math .ceil(Defines.GRANDEZZA_CASELLE_ORIGINALE*GamePanel.SCALA); //L'ultimo numero è il moltiplicatore della telecamera
                Defines.GRANDEZZA_CASELLE = (int) Math .ceil(Defines.SCREEN_HEIGHT/GamePanel.getMaxWorldRow()); //L'ultimo numero è il moltiplicatore della telecamera
            }
            case 2 -> {
                loadMap("map02.txt", "misureMap02.txt", "uscita02.txt", "spawn02.txt");
                Defines.GAME_PANEL.setBackground(Color.gray);
                ambienteAperto=false;
                GamePanel.SCALA=3;
                Defines.PLAYER.setSpeed(2);
                Defines.GRANDEZZA_CASELLE = (int) Math .ceil(Defines.GRANDEZZA_CASELLE_ORIGINALE*GamePanel.SCALA); //L'ultimo numero è il moltiplicatore della telecamera
            }
            default -> {
                loadMap("map01.txt", "misureMap01.txt", "uscita01.txt", "spawn01.txt");
                Defines.GAME_PANEL.setBackground(Color.black);
                ambienteAperto=true;
                GamePanel.SCALA=3;
                Defines.PLAYER.setSpeed(4);
                Defines.GRANDEZZA_CASELLE = (int) Math .ceil(Defines.GRANDEZZA_CASELLE_ORIGINALE*GamePanel.SCALA); //L'ultimo numero è il moltiplicatore della telecamera
                Defines.GRANDEZZA_CASELLE = (int) Math .ceil(Defines.GRANDEZZA_CASELLE_ORIGINALE * GamePanel.SCALA); //L'ultimo numero è il moltiplicatore della telecamera
            }
        }
        //carica la mappa in base alla mappa attuale
        if (uscitaIndex >= 0 && uscitaIndex < spawn.length)
            setPosizionePlayer(spawn[uscitaIndex].getCol(), spawn[uscitaIndex].getRow()); //sposta il player nella posizione di spawn
    }

    //Caricamento mappa

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
   

    //Metodo per stampare la mappa

    public void draw(Graphics2D g) {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
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

                Defines.CAMERA.update(); // crea una nuova telecamera
                Defines.CAMERA.setCameraCasella(col, row); //setta la posizione dell'oggetto da visualizzare
                int tileNum = n[col][row];
                if (ambienteAperto) {
                    int temp = Defines.SCREEN_WIDTH/2 - GamePanel.getMaxWorldCol()*Defines.GRANDEZZA_CASELLE/2;
                    g.translate(temp, 0);
                    g.drawImage(tileset.getTile(tileNum), col*grandezzaCaselle, row*grandezzaCaselle, grandezzaCaselle, grandezzaCaselle, null);
                    g.translate(-temp, 0);
                }else{
                    if (isVisible(Defines.CAMERA)) {
                        g.translate(Defines.CAMERA.getScreenX(), Defines.CAMERA.getScreenY());
                        g.drawImage(tileset.getTile(tileNum), 0, 0, grandezzaCaselle, grandezzaCaselle, null); //disegna il tile enlla posizione calcolata
                        g.translate(-Defines.CAMERA.getScreenX(), -Defines.CAMERA.getScreenY());
                    }
                }
                /*if (isVisible(Defines.CAMERA)) {
                    g.drawImage(tileset.getTile(tileNum), Defines.CAMERA.getScreenX(), Defines.CAMERA.getScreenY(), grandezzaCaselle, grandezzaCaselle, null); //disegna il tile enlla posizione calcolata
                }*/
            }
        }
    }
}
