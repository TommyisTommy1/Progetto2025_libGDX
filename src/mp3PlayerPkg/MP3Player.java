package mp3PlayerPkg;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.File;
import utils.Waitress;

public class MP3Player implements Runnable {
    private AdvancedPlayer player;
    private ArrayList<File> playlist = new ArrayList<>();
    private int i = 0;
    private boolean stop = false;
    private String relativePath = "src/res/audio";
    private File folder = new File(relativePath);

    public MP3Player() {
        loadPlaylist();
    }

    public void loadPlaylist ()
    {
        

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }
        for (int i = 0; i < 8; i++) {
            String path = "";
            switch (i) {
                case 0:
                    path = relativePath + "/PBC/WLR";
                    break;
                case 1:
                    path = relativePath + "/PBC/DieLit";
                    break;
                case 2:
                    path = relativePath + "/PBC/rand";
                    break;
                case 3:
                    path = relativePath + "/PBC/SelfNamed";
                    break;
                case 4:
                    path = relativePath + "/Future/PLUTO";
                    break;
                case 5:
                    path = relativePath + "/Future/WDTY";
                    break;
                case 6:
                    path = relativePath + "/Yeat/2093";
                    break;
                case 7:
                    path = relativePath + "/Yeat/LYFESTYLE";
                    break;
                default:
                    break;
            }
            
            File tempFolder = new File(path);
            for (File file : tempFolder.listFiles()) {
                if (file.getName().toLowerCase().endsWith(".mp3")) {
                    playlist.add(file);
                    System.out.println("Added: " + file.getName());
                }
            }
        }   
        if (playlist.isEmpty()) {
            System.out.println("No MP3 files found in: " + folder.getAbsolutePath());
        }
    }

    @Override
    public void run() {
        while (!stop){
            if (playlist.isEmpty()) {
                System.out.println("No MP3 files found in: " + folder.getAbsolutePath());
                return;
            }

            try (FileInputStream fileInputStream = new FileInputStream(playlist.get((int)(Math.random()*playlist.size())))) {
                player = new AdvancedPlayer(fileInputStream);
                System.out.println("Playing: " + playlist.get((int)(Math.random()*playlist.size())));
                player.play();
            } catch (Exception e) { 
                e.printStackTrace();
                System.out.println("Error playing: " + playlist.get((int)(Math.random()*playlist.size())));
            }
            i++;
            if (i == playlist.size()) {
                i = 0;
            }
        }
    }

    public void stop() {
        if (player != null) {
            stop = true;
            player.close();
        }
    }

    public static void main(String[] args) {
        MP3Player player = new MP3Player();
        Thread playerThread = new Thread(player);
        playerThread.start();
        if(playerThread.isAlive()){
            System.out.println("Il thread è attivo");
        }
        Waitress wait = new Waitress(50000);
        wait.run();
        player.stop();
    }
}
