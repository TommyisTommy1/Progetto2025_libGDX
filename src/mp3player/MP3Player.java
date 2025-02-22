package mp3player;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.File;
public class MP3Player implements Runnable {
    private AdvancedPlayer player;
    private File audio;
    private boolean stop = false;
    private String relativePath = "src/res/audio";
    private File folder = new File(relativePath);
    private String[] tracce = new String[5];

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
    }

    public void changeTrack (int traccia)
    {
        loadPlaylist(traccia);
    }
    @Override
    public void run() {
        while (!stop){
            if (folder.listFiles().length == 0) {
                System.out.println("No MP3 files found in: " + folder.getAbsolutePath());
                return;
            }

            try (FileInputStream fileInputStream = new FileInputStream(audio)) {
                player = new AdvancedPlayer(fileInputStream);
                System.out.println("Playing: " + audio.getName());
                player.play();
            } catch (Exception e) {
                System.out.println("Error playing: " + audio.getName());
            }
        }
    }

    public void stopPlayer(boolean stop) {
        if (stop) {
            stop = true;
            player.close();
        } else {
            stop = false;
        }
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
