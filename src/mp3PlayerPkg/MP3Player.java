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

        for (File file : folder.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".mp3")) {
                playlist.add(file);
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
