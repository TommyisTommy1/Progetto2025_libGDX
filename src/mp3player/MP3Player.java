package mp3player;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.File;
public class MP3Player implements Runnable {
    private AdvancedPlayer player;
    private File audio;
    private String relativePath = "src/res/audio";
    private File folder = new File(relativePath);
    private String[] tracce = new String[5];
    private FileInputStream inputStream;
    public MP3Player(int traccia) {
        tracce[1] = "circusTheme.mp3";
        tracce[2] = "combatTheme.mp3";
        tracce[0] = "darkTheme.mp3";
        tracce[3] = "hostileTheme.mp3";
        tracce[4] = "winterTheme.mp3";
        System.out.println("Loading: " + tracce[traccia]);
        loadPlaylist(traccia);
    }

    public void loadPlaylist (int traccia)
    {
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }
            
        for (File file : folder.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".mp3")) {
                if (file.getName().equals(tracce[traccia])) {
                audio = file;
                System.out.println("Added: " + file.getName());
                }
            } else 
            {
                System.out.println("Skipping: " + file.getName());
            }
        }
        try {
            inputStream = new FileInputStream(audio);
        } catch (Exception e) {
            System.out.println("Error loading: " + audio.getName());
            e.printStackTrace();
        }  
    }

    public void changeTrack (int traccia)
    {
        loadPlaylist(traccia);
    }
    @Override
    public void run() {
            if (folder.listFiles().length == 0) {
                System.out.println("No MP3 files found in: " + folder.getAbsolutePath());
                return;
            }

            try {
                player = new AdvancedPlayer(inputStream);
                System.out.println("Playing: " + audio.getName());
                player.play();
            } catch (Exception e) {
                System.out.println("Error playing: " + audio.getName());
                e.printStackTrace();
            }
    }

    public void stopPlayer() {
            try {
                inputStream.close();
            } catch (Exception e) {
                System.out.println("Error closing: " + audio.getName());
            }
            player.close();
    }

    /*
    public static void main (String[] args) 
    {
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
    */
    
}
