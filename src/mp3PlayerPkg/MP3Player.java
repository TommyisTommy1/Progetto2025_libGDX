package mp3PlayerPkg;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.File;

public class MP3Player implements Runnable {
    private AdvancedPlayer player;
    private File audioFile;
    private boolean stop = false;
    private String relativePath = "src/res/audio";
    private File folder = new File(relativePath);
    private String[] theme = {"circustheme.mp3", "combattheme.mp3", "darktheme.mp3", "hostiletheme.mp3", "wintertheme.mp3"};

    public MP3Player(int themeInt) {
        loadPlaylist(themeInt);
    }

    public void loadPlaylist (int themeInt)
    {
        if (!folder.exists() || !folder.isDirectory()) 
        {
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }
        
        for (File file : folder.listFiles())
        {
            if (file.getName().toLowerCase().endsWith(".mp3"))
            {
                if (file.getName().toLowerCase().equals(theme[themeInt]))
                {
                    this.audioFile = file;
                    System.out.println("Added: " + this.audioFile.getName());
                } else {
                    if (!file.exists())
                    {
                        System.out.println("File not found: " + file.getName());
                    }
                }
            } else {
                System.out.println("Not an mp3 file: " + file.getName());
            }
        }

    }

    @Override
    public void run() 
    {
        while (!stop)
        {
            try (FileInputStream fileInputStream = new FileInputStream(this.audioFile))
            {
                player = new AdvancedPlayer(fileInputStream);
                System.out.println("Playing: " + audioFile.getName());
                player.play();
            } catch (Exception e)
            {
                System.out.println("Error playing: " + audioFile.getName());
            }
        }
    }

    public void stop() {
        if (player != null) {
            stop = true;
            player.close();
        }
    }

    /* 
    public static void main(String[] args) {
        MP3Player player = new MP3Player(1);
        Thread playerThread = new Thread(player);
        playerThread.start();
        if (playerThread.isAlive()) {
            System.out.println("Il thread è attivo");
        }
        Waitress wait = new Waitress(50000);
        wait.run();
        player.stop();
    }
    */
}
